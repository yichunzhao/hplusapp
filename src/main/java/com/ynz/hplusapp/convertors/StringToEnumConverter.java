package com.ynz.hplusapp.convertors;

import com.ynz.hplusapp.beans.Gender;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Gender> {
    @Override
    public Gender convert(String s) {
        Gender result = null;
        switch (s) {
            case "Male":
                result = Gender.MALE;
                break;
            case "Female":
                result = Gender.FEMALE;
                break;
            case "Others":
                result = Gender.OTHER;
                break;
        }
        return result;
    }

}
