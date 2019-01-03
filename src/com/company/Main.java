package com.company;


import java.security.KeyException;

import java.util.logging.Logger;

public class Main {




    public static void main(String[] args)
    {


        MajorRepository majorRepository=new MajorRepository();
        QuestionRespository questionRespository=new QuestionRespository();
        try {
            majorRepository.readData("majors.csv");
        } catch (KeyException e) {
            Logger.getGlobal().warning("Error, bad data in source file."+e.getMessage());
            e.printStackTrace();
        }
    }


}
