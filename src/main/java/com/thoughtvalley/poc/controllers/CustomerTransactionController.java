package com.thoughtvalley.poc.controllers;

import com.thoughtvalley.poc.constants.UserRestURIConstants;
import com.thoughtvalley.poc.models.Transaction;
import com.thoughtvalley.poc.service.CustomerTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/transactions")
public class CustomerTransactionController {

    public CustomerTransactionController() {
    }

    private CustomerTransactionService  customerTransactionService;

    @Autowired
    public CustomerTransactionController(CustomerTransactionService customerTransactionService) {
        this.customerTransactionService = customerTransactionService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Transaction transaction){
        customerTransactionService.save(transaction);

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Transaction> fetchAll(){
        return customerTransactionService.fetchAll();

    }

}
