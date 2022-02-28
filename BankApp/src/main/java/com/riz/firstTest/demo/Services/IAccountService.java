package com.riz.firstTest.demo.Services;

import com.riz.firstTest.demo.Models.Account;

public interface IAccountService {

    public void saveOrUpdateAccout(Account account);
    public void getCurrentAccountByClentId(Long clientId);
    public double updatedBalance(Account account, double transactionAmount, String type);
    public Account findAccountById(Long id);
}
