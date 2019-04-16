package models.fileReader.parsers;

import models.builders.residenceInsurance.ResidenceBuilder;
import models.builders.residenceInsurance.SecondaryResidenceInsuranceBuilder;
import models.customer.CustomerList;
import models.exceptions.customerExceptions.NoSuchCustomerException;
import models.insurance.residenceInsurance.SecondaryResidenceInsurance;

public class ParseSecondaryResidenceInsurance {

    public void parseSecondaryResidenceInsurance(String[] lineArray) {
        SecondaryResidenceInsurance secondaryResidenceInsurance = new SecondaryResidenceInsuranceBuilder()
                .setRegisteredTo(lineArray[0])
                .setAnnualPremium(lineArray[1])
                .setDateOfIssue(lineArray[2])
                .setTotal(lineArray[3])
                .setCoverageDescription(lineArray[4])
                .setPropertyInsuranceAmount(lineArray[5])
                .setAssetsInsuranceAmount(lineArray[6])
                .setResidence(new ResidenceBuilder()
                        .setAddress(lineArray[7])
                        .setYearOfConstruction(lineArray[8])
                        .setType(lineArray[9])
                        .setConstructionMaterial(lineArray[10])
                        .setCondition(lineArray[11])
                        .setSqMeters(lineArray[12])
                        .build())
                .build();
        try {
            CustomerList.addInsuranceToCustomer(secondaryResidenceInsurance);
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
        }
    }
}
