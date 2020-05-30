import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        System.out.print("File to read: ");
        String file = scanner.nextLine();
        System.out.println("Commands:");
        System.out.println("list - lists the recipes");
        System.out.println("stop - stops the program");
        try ( Scanner fileScanner = new Scanner(Paths.get(file))) {
            while (true) {
                System.out.println("Enter command: ");
                String command = scanner.nextLine();
                if (command.equals("stop")) {
                    break;
                }
                if (command.equals("list")) {
                    System.out.println("Recipes:");
                    System.out.println(fileScanner.nextLine()+", cooking time: "+fileScanner.nextLine());
                    while (fileScanner.hasNextLine()) {
                       if (fileScanner.nextLine().isBlank()){
                           System.out.println(fileScanner.nextLine()+", cooking time: "+fileScanner.nextLine());
                       }
                    }

                }
                if (command.equals("find name")){
                    System.out.print("Searched word: ");
                    String word=scanner.nextLine();
                    while(fileScanner.hasNextLine()){
                        if (fileScanner.next().contains(word)){
                            System.out.println(fileScanner.next()+", cooking time: "+fileScanner.nextLine());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
