package com.company;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyException;
import java.util.*;

public class MajorRepository {
    private Set<Major>kierunki;

    public MajorRepository() {

        kierunki=new HashSet<>();
    }
    public void readData(String fileName,Set<Major>results)throws KeyException {

        String[]header=null;

        try(Scanner in=new Scanner(new File(fileName));)
        {

            String headerLine=in.nextLine();
            header= headerLine.split(",");
            Map<String,Boolean>defaultFeatures=new HashMap<>();
            makeDefaultFatures(header, defaultFeatures);


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
                    this.kierunki.add(newMajor);
                }
            }
            catch(EOFException eof)
            {

            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private void makeDefaultFatures(String[] header, Map<String, Boolean> defaultFeatures) throws KeyException {
        for(String head:header)
        {
            if(defaultFeatures.get(head)!=null)
            {
                throw new KeyException();
            }
            defaultFeatures.put(head,Boolean.FALSE);
        }
    }

}
