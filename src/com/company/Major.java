package com.company;

import sun.rmi.runtime.Log;

import java.security.KeyException;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class Major implements Comparable<Major>{

    private String name,university;
    private Map<String,String> features;
    private int weight;
    boolean active;

    @Override
    public int compareTo(Major o) {
        if(weight>o.weight)
            return 1;
        if(weight==o.weight)
        {
            return 0;
        }
        else
            return -1;
    }

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
        this.weight=0;
        this.active=true;
    }

    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }
    public String getFeatureValue(String featureName)
    {
        return features.get(featureName);
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

    public boolean hasFeature(String featureName, String featureValue) {
        String featureResultValue=features.get(featureName);
        boolean result=true;

        if(featureValue.equals("1"))
        {
            result=featureResultValue.equals("1") || featureResultValue.equals("2");
        }
        else
            result=features.get(featureName).equals(featureValue);
        return result;
    }

    public void riseWeight() {
        if(active)
            Logger.getGlobal().info("rise "+name);

        weight++;
    }
    public void disableMajor(String featureName)
    {
        if(features.get(featureName).equals("2"))
            return;
        else {
            if(active)
                Logger.getGlobal().info("disable "+name);
            active=false;
        }

    }
}
