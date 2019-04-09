package models.filewriter.classwriter;

import models.insurance.Insurance;

public class InsuranceWriter implements WriteClassToCsv<Insurance> {

    @Override
    public String generateHeader() {
        return null;
    }

    @Override
    public String write(Insurance insurance) {
        return String.join(";", insurance.getFieldValuesAsStrings());
    }
}
