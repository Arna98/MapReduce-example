import java.io.*;
import java.util.HashMap;
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
                        output.write(String.format("%s\n", url));
                    }
                }
            }
            br.close();
            output.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    static void reducer(){
        // Definition of variables
        String url;
        HashMap<String, Integer> keyValue = new HashMap<String, Integer>();

        try{
            // Read text file
            BufferedReader br = new BufferedReader(new FileReader("output.txt"));
            // Write to final output file
            FileWriter finalOutput = new FileWriter("finalOutput.txt", true);
            // Get the key(url) of the buffered reader to perform reduction operations
            while((url = br.readLine()) != null){
                if(keyValue.containsKey(url)){
                    keyValue.put(url, keyValue.get(url) + 1);
                }else{
                    keyValue.put(url, 1);
                }
            }
            br.close();
            // Remove the intermediate output
            rmOutput();
            // Save and write to final output file
            for (String key : keyValue.keySet()) {
                finalOutput.write(String.format("<%s, %d>\n", key, keyValue.get(key)));
            }
            finalOutput.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // Removing the intermediate output produced by the mapper method
    private static void rmOutput(){
        File output = new File("output.txt");
        if (output.exists()){
            output.delete();
        }
    }
}