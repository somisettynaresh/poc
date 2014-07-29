package com.thoughtvalley.poc.controllers;

/**
 * Created by Naresh on 7/21/2014.
 */

import com.thoughtvalley.poc.util.CustomerContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TestController {

    private static final Logger log = Logger.getLogger(TestController.class);

    @RequestMapping(value="/test",method=RequestMethod.GET)
    public String getHomePage()
    {
        log.info("getHomePage() method invoked");

        return "index";
    }

    @RequestMapping(value="/test/{custId}",method=RequestMethod.GET)
    public String setCustomerId(@PathVariable String custId)
    {
//        CustomerContext.setDefaultCustomerId(custId);
        log.info(" *********** cust id = "+custId+" ***************");

        return custId;
    }
}
