package com.example.banking_app;

import com.example.banking_app.dto.BankAccountDTO;
import com.example.banking_app.dto.Deposit;
import com.example.banking_app.dto.Transfer;
import com.example.banking_app.dto.Withdraw;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.math.BigDecimal;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestAccountController {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllAccountsAPI() throws Exception
    {
        mvc.perform(
                        get("/api/v1/account")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[8]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].number").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].balance").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].userId").isNotEmpty())
        ;
    }

    @Test
    public void getAccountByIdAPI() throws Exception
    {
        mvc.perform(
                        get("/api/v1/account/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void createAccountAPI() throws Exception
    {
        BankAccountDTO bankAccountDTO = new BankAccountDTO("test1234", new BigDecimal("100000"), 3L);
        String bankAccountDTOJson = asJsonString(bankAccountDTO);
        mvc.perform(
                        post("/api/v1/account")
                        .content(bankAccountDTOJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10L))
                ;
    }

    @Test
    public void depositToAccountAPI() throws Exception
    {

        Deposit deposit = new Deposit(new BigDecimal("124"));
        String depositJson = asJsonString(deposit);
        mvc.perform(
                put("/api/v1/account/{id}/deposit",9)
                        .content(depositJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(900124))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").isNotEmpty());
    }

    @Test
    public void withdrawFromAccountAPI() throws Exception
    {

        Withdraw withdraw = new Withdraw(new BigDecimal("1"));
        String depositJson = asJsonString(withdraw);
        mvc.perform(
                        put("/api/v1/account/{id}/withdraw",8)
                                .content(depositJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(799999))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").isNotEmpty());
    }

    @Test
    public void transferFromAccountAPI() throws Exception
    {

        Transfer transfer = new Transfer(new BigDecimal("20"), "20000001");
        String depositJson = asJsonString(transfer);
        mvc.perform(
                        put("/api/v1/account/{id}/transfer", 3)
                                .content(depositJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value("10000003"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(299980))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1L));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
