package com.company;


import java.security.KeyException;

import java.util.logging.Logger;

public class Main {




    public static void main(String[] args)
    {


        MajorRepository majorRepository=new MajorRepository();
        QuestionRespository questionRespository=new QuestionRespository();
        loadData(majorRepository, questionRespository);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HelloFrame window= new HelloFrame();
                window.setVisible(true);
            }
        });
    }

    private static void loadData(MajorRepository majorRepository, QuestionRespository questionRespository) {
        try {
            majorRepository.readData("majors.csv");
            questionRespository.readData("questions.csv");
        } catch (KeyException e) {
            Logger.getGlobal().warning("Error, bad data in source file."+e.getMessage());
            e.printStackTrace();
        }
        Logger.getGlobal().info("all data load");
    }


}
