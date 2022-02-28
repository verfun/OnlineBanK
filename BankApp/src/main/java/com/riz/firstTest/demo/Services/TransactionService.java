package com.riz.firstTest.demo.Services;

import com.riz.firstTest.demo.Models.Account;
import com.riz.firstTest.demo.Models.Transaction;
import com.riz.firstTest.demo.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService implements ITransactionService{
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    public void saveOrUpdateTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Transaction updateTransactionData(Transaction transaction, Account currentAccount, String status) {
        transaction.setDate(new Date());
        transaction.setAccount(currentAccount);
        transaction.setPostBalance(currentAccount.getBalance());
        transaction.setStatus(status);
        return transaction;
    }

    public Transaction addAccountTransaction(Transaction transaction) {
        String status = "Refused";
        Account currentAccount = accountService.findAccountById(accountService.clientAccountID);
        boolean updatedAccountStatus = accountService.updateBalance(currentAccount, transaction.getAmount(), transaction.getType());
        if(updatedAccountStatus) {
            status = "Accepted";
        }
        Transaction updatedTransaction = updateTransactionData(transaction, currentAccount, status);
        currentAccount.getTransactions().add(updatedTransaction);
        accountService.saveOrUpdateAccout(currentAccount);
        return updatedTransaction;
    }
}
