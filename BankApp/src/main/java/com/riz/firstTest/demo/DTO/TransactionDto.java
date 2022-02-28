package com.riz.firstTest.demo.DTO;

import com.riz.firstTest.demo.Models.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDto {
    private long transactionNumber;
    private Date date;
    private String description;
    private String type;
    private double amount;
    private double balance;

    TransactionDto(Transaction transaction) {
        this.amount = transaction.getAmount();
        this.transactionNumber = transaction.getAccount().getId() * 100 + transaction.getId();
        this.date = transaction.getDate();
        this.balance = transaction.getPostBalance();
        this.type = transaction.getType().toString();
        this.description = transaction.getDescription();
    }

    public static List<TransactionDto> getDto(List<Transaction> transactions) {
        //return transactions.stream().map(t -> new TransactionDto(t) ).collect(Collectors.toList());
        List<TransactionDto> list = new ArrayList<>();
        for(Transaction t : transactions) {
            list.add(new TransactionDto(t));
        }
        return list;
    }
}
