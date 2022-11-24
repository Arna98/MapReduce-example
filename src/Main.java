import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        mapper();
        reducer();
    }
    static void mapper(){
        // Definition of variables
        String line;
        String[] words;
        String tokenize = "\\s*,\\s*";
        Pattern regexUrl = Pattern.compile(
                "^https?://(www\\.)?[-a-zA-Z0-9]{1,256}\\.[a-zA-Z0-9]{1,6}\\b([a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$"
        );

        try{
            // Read csv file
            BufferedReader br = new BufferedReader(new FileReader("input.csv"));
            // Write to output file
            FileWriter output = new FileWriter("output.txt", true);
            // Get the data of each line of the buffered reader
            while((line = br.readLine()) != null){
                words = line.split(tokenize);
                // Analyze and review each word
                for (String word : words) {
                    Matcher matcherUrl = regexUrl.matcher(word);
                    if (matcherUrl.find()) {
                        // Clear data and write to output file
                        String url = word.replaceAll("(^https?://)?(www\\.)?","");
                        output.write(String.format("%s, %d\n", url, 1));
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    static void reducer(){
        // Definition of variables
        String line;
        String[] words;
        HashMap<String, Integer> keyValue = new HashMap<String, Integer>();

    }
}