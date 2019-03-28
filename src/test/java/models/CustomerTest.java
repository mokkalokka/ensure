package models;

import models.customer.Customer;
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
        Customer customer1 = new Customer("Joe", "Biden");
        Customer customer2 = new Customer("Hei", "sann");
        Customer customer3 = new Customer(", ", "fasdf");
        assertEquals(customer1.getInsuranceNr() + 1, customer2.getInsuranceNr());
        assertEquals(customer1.getInsuranceNr() + 2, customer3.getInsuranceNr());
        assertEquals(10000, customer1.getInsuranceNr());
    }
}