package com.qa.utils;

import java.nio.file.*;
import java.util.*;

public class ReadCSV {
    public static void main(String[] args){
        try{
            List<String> lines = Files.readAllLines(Paths.get("e96d80fc-787a-4902-b470-eabcacddfbd9\\data (1).csv"));
            for(String line: lines){
                line = line.replace("\"=\"\"", "");
                String[] result = line.split(",");
                for(String s: result)
                    System.out.println(s );
                System.out.println(line);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
