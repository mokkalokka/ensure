package models;

import models.customer.CustomerHandling;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CustomerHandelingTest {

    @Test
    public void stringContainsNumbers() {
        CustomerHandling customerHandling = new CustomerHandling();
        assertTrue(customerHandling.stringContainsNumbers("13"));
        assertFalse(customerHandling.stringContainsNumbers("Roger"));




    }
}