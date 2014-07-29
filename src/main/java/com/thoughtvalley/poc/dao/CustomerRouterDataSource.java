package com.thoughtvalley.poc.dao;

import com.thoughtvalley.poc.util.CustomerContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class CustomerRouterDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return CustomerContext.getCustomerId();
    }
}
