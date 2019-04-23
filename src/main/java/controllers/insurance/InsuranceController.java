package controllers.insurance;

import controllers.detailedCustomerController;
import models.customer.Customer;
import models.insurance.Insurance;

public interface InsuranceController {

    Customer getCustomer();

    void setCustomer(Customer customer);

    EmbeddedFieldsController getEmbeddedFieldsController();

    Insurance getNewInsurance();

    Insurance getEditedInsurance();

    void load();

    void setState(InsuranceState state);

    void setInsurance(Insurance insurance);

    void loadInsurance();

    void setParent(detailedCustomerController parent);
}
