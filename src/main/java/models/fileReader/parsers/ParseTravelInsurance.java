package models.fileReader.parsers;

import models.builders.travelInsurance.TravelInsuranceBuilder;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.travelInsurance.TravelInsurance;

public class ParseTravelInsurance {
    public void parseTravelInsurance(String[] lineArray) {
        TravelInsurance travelInsurance = new TravelInsuranceBuilder()
                .setRegisteredTo(lineArray[0])
                .setAnnualPremium(lineArray[1])
                .setDateOfIssue(lineArray[2])
                .setMaxCoverage(lineArray[3])
                .setCoverageDescription(lineArray[4])
                .setPremium(lineArray[5])
                .setTotal(lineArray[6])
                .build();

        try {
            CustomerList.addInsuranceToCustomer(travelInsurance);
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
        }
    }
}
