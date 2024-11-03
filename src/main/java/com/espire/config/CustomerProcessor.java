package com.espire.config;

import com.espire.model.Customer;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer,Customer> {


    @Override
    public Customer process(Customer item) throws Exception {
        System.out.println(Integer.parseInt(item.getIndex()));
        return item;
    }
}
