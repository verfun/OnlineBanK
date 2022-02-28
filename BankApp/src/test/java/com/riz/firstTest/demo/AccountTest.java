package com.riz.firstTest.demo;


import com.riz.firstTest.demo.Models.Account;
import com.riz.firstTest.demo.Repositories.AccountRepository;
import com.riz.firstTest.demo.Repositories.TransactionRepository;
import com.riz.firstTest.demo.Services.AccountService;
import com.riz.firstTest.demo.Services.TransactionService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    AccountService accountService;

    @ParameterizedTest
    @CsvSource({
            "2,200, 1200,'DEPOSIT',1400",
            "2,220, 1600,'DEPOSIT',1820",
            "2,100, 1600,'WITHDRAWAL',1500",
            "2,1900, 1600,'WITHDRAWAL',1600"
    })
    void updateAccountBalance(Long accountId, double amount, double balance, String type, double expectedBalance) {
        Account account = new Account(balance);
        Mockito.when(accountRepository.findById(accountId)).thenReturn(java.util.Optional.of(account));
        Account currentAccount = accountService.findAccountById(accountId);
        boolean status = accountService.updateBalance(currentAccount, amount, type);
        assertThat(currentAccount.getBalance()).isEqualTo(expectedBalance);
    }

    @ParameterizedTest
    @CsvSource({
            "200, 1200,'DEPOSIT', 1400",
            "2009, 1200,'WITHDRAWAL', -809"
    })
    void calculateBlance(double amount,double balance, String type, double expectedBalance) {
        Account account = new Account(balance);
        double newBalance = accountService.updatedBalance(account, amount,type);
        assertThat(newBalance).isEqualTo(expectedBalance);
    }
}
