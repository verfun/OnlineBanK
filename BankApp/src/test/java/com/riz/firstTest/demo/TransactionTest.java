package com.riz.firstTest.demo;


import com.google.common.base.Verify;
import com.riz.firstTest.demo.Models.Account;
import com.riz.firstTest.demo.Models.Transaction;
import com.riz.firstTest.demo.Repositories.TransactionRepository;
import com.riz.firstTest.demo.Services.AccountService;
import com.riz.firstTest.demo.Services.TransactionService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransactionTest {

    @Mock
    private AccountService accountService;
    @InjectMocks
    TransactionService transactionService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.autoCloseable.close();
    }

    @ParameterizedTest
    @CsvSource({
            "1,200, 1200,'DEPOSIT','Accepted'",
            "1,220, 1600,'DEPOSIT','Accepted'",
            "1,100, 1600,'WITHDRAWAL','Accepted'",
            "1,1900, 1600,'WITHDRAWAL','Refused'"
    })
    void updateAccountBalance(Long accountId,double amount, double balance, String type, String expectedStatus) {
        Account account = new Account(balance);
        Mockito.when(accountService.findAccountById(accountId)).thenReturn(account);
        double newBalance = type.equals("WITHDRAWAL") ? balance-amount : balance+amount;
        boolean updatedAccountStatus = newBalance < 0 ? false : true;
        Mockito.when(accountService.updateBalance(account, amount, type)).thenReturn(updatedAccountStatus);
        Transaction newTransaction = new Transaction(amount, type, balance);
        Transaction addedTransaction = transactionService.addAccountTransaction(newTransaction);
        assertThat(addedTransaction.getStatus()).isEqualTo(expectedStatus);
    }

    @ParameterizedTest
    @CsvSource({
            "200, 1200,'DEPOSIT','Accepted'",
            "220, 1600,'DEPOSIT','Accepted'",
            "100, 1600,'WITHDRAWAL','Accepted'",
            "1900, 1600,'WITHDRAWAL','Refused'"
    })
    void updateTransactionData(double amount, double balance, String type, String status) {
        Account account = new Account(balance);
        Transaction tr = new Transaction(amount, type, balance);
        transactionService.updateTransactionData(tr,account, status);
        assertThat(tr.getAccount().getBalance()).isEqualTo(balance);
        assertThat(tr.getStatus()).isEqualTo(status);
    }
}
