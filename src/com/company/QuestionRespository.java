package com.company;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class QuestionRespository{
    private ArrayList<Question> questions;

    public QuestionRespository()
    {
        questions =new ArrayList<>();
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
            in.nextLine(); //skip header
            String record=null;
            while (in.hasNextLine()) {
                record=in.nextLine();
                String[] recordContent=record.split(",");
                String[]answers={recordContent[1],recordContent[2],recordContent[3]};
                Question newQuestion=new Question(recordContent[4],recordContent[0],answers);
                questions.add(newQuestion);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        Logger.getGlobal().info("questions has been load");

    }



}
