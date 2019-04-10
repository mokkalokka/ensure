package models;

import models.customer.CustomerHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerHandelingTest {

    @Test
    public void stringContainsNumbers() {
        CustomerHandler customerHandler = new CustomerHandler();
        assertTrue(customerHandler.stringContainsNumbers("13"));
        assertFalse(customerHandler.stringContainsNumbers("Roger"));




    }
}