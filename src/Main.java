import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mapper();
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

}