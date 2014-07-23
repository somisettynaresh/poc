package com.thoughtvalley.poc.service.impl;

import com.thoughtvalley.poc.dao.TransactionDao;
import com.thoughtvalley.poc.models.Transaction;
import com.thoughtvalley.poc.service.CustomerTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTransactionServiceImpl implements CustomerTransactionService {

    private TransactionDao transactionDao;

    public CustomerTransactionServiceImpl() {
    }

    @Autowired
    public CustomerTransactionServiceImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public void save(Transaction transaction) {
        transactionDao.save(transaction);
    }

    @Override
    public List<Transaction> fetchAll() {
        return null;
    }

    @Override
    public List<Transaction> findTransactions(Transaction searchParams) {
        return null;
    }
}
