package models;

import models.customer.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CustomerTest {
    Customer customer1, customer2, customer3;

    @Before
    public void setUp() throws Exception {
        customer1 = new Customer("Joe", "Biden", "Trondheimsveien 2");
        customer2 = new Customer("Hei", "sann", "Ullevålsveien 53");
        customer3 = new Customer("Trude ", "Sagen", "Vogts gate 2");
    }

    @Test
    public void getCustomerSince() {
        Date now = new Date();
        assertEquals(now, customer1.getCustomerSince());
    }

    @Test
    public void getInsuranceNr() {

    }

    @Test
    public void getNextInsuranceNr() {
        assertEquals(customer1.getInsuranceNr() + 1, customer2.getInsuranceNr());
        assertEquals(customer1.getInsuranceNr() + 2, customer3.getInsuranceNr());
    }
}