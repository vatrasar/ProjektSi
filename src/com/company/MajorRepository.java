package com.company;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyException;
import java.util.*;

public class MajorRepository {
    private Set<Major>majors;

    public MajorRepository() {

        majors=new HashSet<>();
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
    }

    /**
     * Method read majors from strream in and then save it to majors pole
     * @param header
     * @param in
     * @param defaultFeatures
     * @throws KeyException
     */
    private void readRecords(String[] header, Scanner in, Map<String, String> defaultFeatures) throws KeyException {
        try {
            String record=null;
            while (in.hasNextLine()) {
                record=in.nextLine();
                String[] recordContent=record.split(",");// table contents successive fields of record
                if(recordContent.length==0)
                {
                    throw new EOFException();
                }
                Major newMajor =new Major(recordContent[0],recordContent[1],new HashMap<>(defaultFeatures));
                for(int i=2;i<recordContent.length;i++)
                {
                   newMajor.setFeatureValue(header[i],recordContent[i]);
                }
                this.majors.add(newMajor);
            }
        }
        catch(EOFException eof)
        {

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

}
