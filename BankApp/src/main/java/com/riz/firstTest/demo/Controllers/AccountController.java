package com.riz.firstTest.demo.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Accounts")
public class AccountController {

    @GetMapping("/all")
    public String getAllAccounts() {
        return "Hello World";
    }
}
