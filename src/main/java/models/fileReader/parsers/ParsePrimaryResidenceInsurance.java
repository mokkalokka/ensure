package models.fileReader.parsers;

import models.builders.residenceInsurance.PrimaryResidenceInsuranceBuilder;
import models.builders.residenceInsurance.ResidenceBuilder;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.residenceInsurance.PrimaryResidenceInsurance;

public class ParsePrimaryResidenceInsurance {
    public void parsePrimaryResidenceInsurance(String[] lineArray) {

        PrimaryResidenceInsurance primaryResidenceInsurance = new PrimaryResidenceInsuranceBuilder()
                .setRegisteredTo(lineArray[0])
                .setAnnualPremium(lineArray[1])
                .setDateOfIssue(lineArray[2])
                .setTotal(lineArray[3])
                .setCoverageDescription(lineArray[4])
                .setInsuranceID(lineArray[5])
                .setPropertyInsuranceAmount(lineArray[6])
                .setAssetsInsuranceAmount(lineArray[7])
                .setResidence(new ResidenceBuilder()
                        .setAddress(lineArray[8])
                        .setYearOfConstruction(lineArray[9])
                        .setType(lineArray[10])
                        .setConstructionMaterial(lineArray[11])
                        .setCondition(lineArray[12])
                        .setSqMeters(lineArray[13])
                        .build())
                .build();

        try {
            CustomerList.addInsuranceToCustomer(primaryResidenceInsurance);
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
        }
    }
}
