package com.thoughtvalley.poc.service;

import java.util.List;

import com.thoughtvalley.poc.models.Transaction;

public interface CustomerTransactionService {
	void save (Transaction transaction);
	List<Transaction> fetchAll();
	List<Transaction> findTransactions(Transaction searchParams);
	
}
