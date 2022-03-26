package com.example.banking_app;

import com.example.banking_app.controller.AccountController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class TestAccountController {

    @Autowired
    private MockMvc mvc;


}
