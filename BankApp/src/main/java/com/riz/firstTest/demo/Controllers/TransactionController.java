package com.riz.firstTest.demo.Controllers;

import com.riz.firstTest.demo.Models.Account;
import com.riz.firstTest.demo.Models.Transaction;
import com.riz.firstTest.demo.Services.AccountService;
import com.riz.firstTest.demo.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class TransactionController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/{accountId}", produces=MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<List<Transaction>> getTransaction(@PathVariable(name = "accountId") Long accountId) {
        Account currentAccount = accountService.findAccountById(accountId);
        if(currentAccount!=null) {
            return ResponseEntity.ok(currentAccount.getTransactions());
        }
        return null;
    }

    @GetMapping(value = "/all")
    public  ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAll());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.addAccountTransaction(transaction), HttpStatus.CREATED);
    }
}
