package com.thoughtvalley.poc.util;

import java.util.HashMap;
import java.util.Map;

public class CustomerContext {
    private static String currentCustomer = "admin";
    private static Map<String, String> customers = new HashMap<String, String>() {{
        put("admin", "c1");
        put("naresh", "c2");
    }};


    public static String getCustomerId() {
        return customers.get(currentCustomer);
    }

    public static void setCurrentCustomer(String currentCustomer) {
        CustomerContext.currentCustomer = currentCustomer;
    }

}
