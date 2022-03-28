package com.example.banking_app;

import com.example.banking_app.dto.BankAccountDTO;
import com.example.banking_app.dto.Deposit;
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
        BankAccountDTO bankAccountDTO = new BankAccountDTO("1234", new BigDecimal("30"), 3L);
        String bankAccountDTOJson = asJsonString(bankAccountDTO);
        mvc.perform(
                        post("/api/v1/account")
                        .content(bankAccountDTOJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                ;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void depositToAccountAPI() throws Exception
    {

        Deposit deposit = new Deposit(new BigDecimal("123"));
        String depositJson = asJsonString(deposit);
        mvc.perform(
                put("/api/v1/account/{id}/deposit",3)
                        .content(depositJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
