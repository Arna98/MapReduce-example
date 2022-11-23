import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] str = mapper();
        reducer(str);
    }
    static String[] mapper(){
        String[] word;
        String tokenize = "\\s+";
        System.out.print("Enter Input: ");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine().strip().toLowerCase();

        word = str.split(tokenize);

        for ( String key : word) {
            System.out.printf("<%s  ,%s>\n", key, "1");
        }
        System.out.println("\n------------------------------------------------\n");
        return word;
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