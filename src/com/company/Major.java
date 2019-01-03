package com.company;

import java.security.KeyException;
import java.util.Map;

public class Major {

    private String name,university;
    private Map<String,String> features;


    public Major(String name, String university, Map<String,String> features){

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

        features.put(featureName,value);

    }
}
