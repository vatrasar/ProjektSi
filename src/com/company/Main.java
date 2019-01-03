package com.company;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	read_data("plik.csv");
    }

    private static void read_data(String fileName) {

        ArrayList<String> header=null
        try(Scanner in=new Scanner(new File(fileName));)
        {
            try {

                while (true) {
                    String headerLine=in.nextLine();

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
