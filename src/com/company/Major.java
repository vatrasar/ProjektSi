package com.company;

import java.security.KeyException;
import java.util.Map;

public class Major {

    private String name,university;
    private Map<String,Boolean> features;


    public Major(String name, String university, Map<String,Boolean> features){

        this.name = name;
        this.features = features;
        this.university=university;
    }

    public String getName() {
        return name;
    }

    public String getUniversity() {
        return university;
    }
    public void setFeatureValue(String featureName,String value) throws KeyException
    {
        if(features.get(featureName)==null)
        {
            throw new KeyException("no feature with such name");

        }
        Boolean newValue=null;
        switch (value)
        {
            case "1":
                newValue=Boolean.TRUE;
            case "0":
                newValue=Boolean.FALSE;

        }
        features.put(featureName,newValue);

    }
}
