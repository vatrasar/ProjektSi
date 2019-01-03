package com.company;

import java.security.KeyException;
import java.util.Map;
import java.util.Objects;

public class Major {

    private String name,university;
    private Map<String,String> features;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Major major = (Major) o;
        return Objects.equals(name, major.name) &&
                Objects.equals(university, major.university) &&
                Objects.equals(features, major.features);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, university, features);
    }

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
