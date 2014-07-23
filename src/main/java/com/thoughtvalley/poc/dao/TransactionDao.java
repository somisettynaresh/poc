package com.thoughtvalley.poc.dao;

import java.util.List;

import com.thoughtvalley.poc.models.Transaction;

public interface TransactionDao {
	void save(Transaction transaction);
	List<Transaction> fetchAll();
	List<Transaction> findTransactions(Transaction transactionParams);
	void delete(Transaction transaction);
}
