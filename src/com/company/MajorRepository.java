package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MajorRepository {
    private Set<Major>majors;

    public MajorRepository() {

        majors=new HashSet<>();
    }

    public Set<Major> getMajors() {
        return majors;
    }

    /**
     * Method read majors from file with name fileName and then save it to majors pole
     * @param fileName
     * @throws KeyException
     */
    public void readData(String fileName)throws KeyException {

        String[]header=null;

        try(Scanner in=new Scanner(new File(fileName));)
        {

            String headerLine=in.nextLine();
            header= headerLine.split(",");
            Map<String,String>defaultFeatures=new HashMap<>();
            makeDefaultFatures(header, defaultFeatures);
            readRecords(header, in, defaultFeatures);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        Logger.getGlobal().info("Majors has been load");
    }

    /**
     * Method read majors from strream in and then save it to majors pole
     * @param header
     * @param in
     * @param defaultFeatures
     * @throws KeyException
     */
    private void readRecords(String[] header, Scanner in, Map<String, String> defaultFeatures) throws KeyException {

        String record=null;
        while (in.hasNextLine()) {
            record=in.nextLine();
            String[] recordContent=record.split(",");// table contents successive fields of record

            Major newMajor =new Major(recordContent[0],recordContent[1],new HashMap<>(defaultFeatures));
            for(int i=1;i<recordContent.length;i++)
            {
               newMajor.setFeatureValue(header[i],recordContent[i]);
            }
            this.majors.add(newMajor);
        }




    }

    /**
     * to defaultFeatures save all elements from header as key and Boolean.False as value
     * @param header
     * @param defaultFeatures
     * @throws KeyException
     */
    private void makeDefaultFatures(String[] header, Map<String, String> defaultFeatures) throws KeyException {
        for(String head:header)
        {
            if(defaultFeatures.get(head)!=null)
            {
                throw new KeyException(head);
            }
            defaultFeatures.put(head,"0");
        }
    }

    public void riseWeightWhereFeature(final String featureName,final String featureValue) {
        List<Major> majorsToRise = getMajorsWhereFeature(featureName, featureValue);
        majorsToRise.forEach(Major::riseWeight);

    }

    private List<Major> getMajorsWhereFeature(String featureName, String featureValue) {
        final String translatedFeatureValue = translateFeatureValue(featureValue);
        return majors.stream().filter(a -> a.hasFeature(featureName, translatedFeatureValue)).collect(Collectors.toList());
    }

    public String translateFeatureValue(String featureValue) {

        switch (featureValue)
        {
            case "tak":
                return "1";
            case "nie":
                return "0";
        }
        return featureValue;
    }

    public void disableWhereFeature(String featureName, String featureValue) {
        List<Major> majorsToDisable = getMajorsWhereFeature(featureName, featureValue);
        majorsToDisable.forEach(Major::disableMajor);

    }

    /**
     *disable majors with fatureValue and rise Weight majros with featureValue2
     * @param featureName
     * @param featureValue
     * @param featureValue2
     */
    public void switchFeatures(String featureName, String featureValue,String featureValue2) {
        List<Major> majorsToDisable = getMajorsWhereFeature(featureName,featureValue);
        List<Major> majorsToRise =getMajorsWhereFeature(featureName,featureValue2);
        majorsToDisable.forEach(Major::disableMajor);
        majorsToRise.forEach(Major::riseWeight);
    }

    public List<Major> getResults() {

        List<Major>results=majors.stream().filter(Major::isActive).collect(Collectors.toList());
        Collections.sort(results);
        return results;
    }
}
