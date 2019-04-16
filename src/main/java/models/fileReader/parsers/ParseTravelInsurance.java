package models.fileReader.parsers;

import models.builders.travelInsurance.TravelInsuranceBuilder;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.Insurance;
import models.travelInsurance.TravelInsurance;

public class ParseTravelInsurance {
    public static Insurance parseTravelInsurance(String[] lineArray) {
        TravelInsurance travelInsurance = new TravelInsuranceBuilder()
                .setRegisteredTo(lineArray[0])
                .setAnnualPremium(lineArray[1])
                .setDateOfIssue(lineArray[2])
                .setMaxCoverage(lineArray[3])
                .setCoverageDescription(lineArray[4])
                .setInsuranceID(lineArray[5])
                .setPremium(lineArray[6])
                .setTotal(lineArray[7])
                .build();

        return travelInsurance;
    }
}
