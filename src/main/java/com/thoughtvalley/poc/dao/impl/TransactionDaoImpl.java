package com.thoughtvalley.poc.dao.impl;

import java.util.List;


import com.thoughtvalley.poc.dao.TransactionDao;
import com.thoughtvalley.poc.models.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public class TransactionDaoImpl implements TransactionDao {

    private SessionFactory sessionFactory;

	@Override
    @Transactional
	public void save(Transaction transaction) {
		getSessionFactory().getCurrentSession().saveOrUpdate(transaction);
//        getSessionFactory().getCurrentSession().flush();
	}

	@Override
	public List<Transaction> fetchAll() {
		return getSessionFactory().getCurrentSession().createQuery("from Transaction").list();
	}

	@Override
	public List<Transaction> findTransactions(Transaction transactionParams) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void delete(Transaction transaction) {
		// TODO Auto-generated method stub

	}

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
