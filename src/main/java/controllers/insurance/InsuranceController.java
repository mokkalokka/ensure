package controllers.insurance;

import models.customer.Customer;
import models.insurance.Insurance;

public interface InsuranceController {

    Customer getMyCustomer();

    EmbeddedFieldsController getEmbeddedFieldsController();

    Insurance getCurrentInsurance();

    void loadInsurance();

    void setState(InsuranceState state);

    void setMyInsurance(Insurance currentInsurance);
}
