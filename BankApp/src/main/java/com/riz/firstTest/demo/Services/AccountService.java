package com.riz.firstTest.demo.Services;

import com.riz.firstTest.demo.Models.Account;
import com.riz.firstTest.demo.Models.Client;
import com.riz.firstTest.demo.Models.TransactionType;
import com.riz.firstTest.demo.Repositories.AccountRepository;
import com.riz.firstTest.demo.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    public static final Long connectedCLientID = 1l;
    public static final Long clientAccountID = 1l;


    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void saveOrUpdateAccout(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void getCurrentAccountByClentId(Long clientId) {
        Client clt = clientRepository.findById(clientId).get();
        if(clt!=null) {
            clt.getCurrentAccount();
        }
    }

    public synchronized boolean updateBalance(Account account, double transactionAmount, String type) {
        double newBalance = updatedBalance(account, transactionAmount, type);
        if( newBalance >= 0) {
            account.setBalance(newBalance);
            return true;
        }
        return false;
    }

    public double updatedBalance(Account account, double transactionAmount, String type) {
        return type.equals(TransactionType.WITHDRAWAL.toString()) ? account.getBalance() - transactionAmount : account.getBalance() + transactionAmount;
    }

    public Account findAccountById(Long id) {
        return accountRepository.findById(id).get();
    }
}
