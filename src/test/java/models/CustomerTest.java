package models;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void getCustomerSince() {
        Customer customer = new Customer("Joe", "Biden");
        Date now = new Date();
        assertEquals(now, customer.getCustomerSince());
    }

    @Test
    public void getInsuranceNr() {

    }

    @Test
    public void getNextInsuranceNr() {
        Customer customer = new Customer("Joe", "Biden");
        assertEquals(customer.getInsuranceNr() + 1, customer.getNextInsuranceNr());
    }
}