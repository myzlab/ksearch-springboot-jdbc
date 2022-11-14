package com.myzlab.k.optional;

import com.myzlab.k.KColumn;
import com.myzlab.k.KValNumberField;
import com.myzlab.k.KValTextField;

public class KOptionalHelper {

    public static KOptionalKColumn optional(
        final KColumn kColumn
    ) {
        return KOptionalKColumn.getInstance(kColumn);
    }
    
    public static KOptionalKValNumberField optional(
        final KValNumberField kValNumberField
    ) {
        return KOptionalKValNumberField.getInstance(kValNumberField);
    }
    
    public static KOptionalKValTextField optional(
        final KValTextField kValTextField
    ) {
        return KOptionalKValTextField.getInstance(kValTextField);
    }
    
    public static KOptionalNumber optional(
        final Number number
    ) {
        return KOptionalNumber.getInstance(number);
    }
    
    public static KOptionalString optional(
        final String value
    ) {
        return KOptionalString.getInstance(value);
    }
}