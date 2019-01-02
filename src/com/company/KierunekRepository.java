package com.company;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class KierunekRepository {
    ArrayList<Kierunek>kierunki;

    public KierunekRepository() {

        kierunki=new ArrayList<>();
    }
    private static void read_data(String fileName) {

        String[]header=null;
        try(Scanner in=new Scanner(new File(fileName));)
        {
            try {

                String headerLine=in.nextLine();
                header= headerLine.split(",");

                for(int i=1;i<header.length;i++)
                {

                }
                while (true) {

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

}
