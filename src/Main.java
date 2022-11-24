import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        mapper();
        //reducer();
    }
    static void mapper(){
        // Definition of variables
        String line;
        String[] words;
        String tokenize = "\\s*,\\s*";

    }

    static void reducer(String[] input){
        String[] str = input;
        str = new HashSet<String>(Arrays.asList(str)).toArray(new String[0]);
        int count = 0;
        while(count < str.length){
            int counter = 0;
            for(String currentWord : input){
                if(str[count].equals(currentWord)){
                    counter++;
                }
            }
            System.out.printf("<%s, %d>\n", str[count], counter);
            try {
                FileWriter output = new FileWriter("output.txt",true);
                output.write(String.format("<%s, %d>\n", str[count], counter));

                output.close();
                System.out.println("Successfully wrote to the file.");

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            count++;
        }

    }
}