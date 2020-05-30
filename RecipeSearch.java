import java.nio.file.Paths;
import java.util.Scanner;

public class RecipeSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("File to read: ");
        String file = scanner.nextLine();
        System.out.println("Commands:");
        System.out.println("list - lists the recipes");
        System.out.println("stop - stops the program");
        System.out.println("find name - searches recipes by name");
        System.out.println("find cooking time - searches recipes by cooking time");
        System.out.println("find ingredient - searches recipes by ingredient");
        try ( Scanner fileScanner = new Scanner(Paths.get(file))) {
            while (true) {
                System.out.println("Enter command: ");
                String command = scanner.nextLine();
                if (command.equals("stop")) {
                    break;
                }else if (command.equals("list")) {
                    System.out.println("Recipes:");
                    System.out.println(fileScanner.nextLine()+", cooking time: "+fileScanner.nextLine());  //For the first element
                    while (fileScanner.hasNextLine()) {
                       if (fileScanner.nextLine().isBlank()){                                   //For the element after blank line
                           System.out.println(fileScanner.nextLine()+", cooking time: "+fileScanner.nextLine());
                       }
                    }
                }else if (command.equals("find name")){
                    System.out.print("Searched word: ");
                    String word=scanner.nextLine();
                    while(fileScanner.hasNextLine()){
                        String recipeName = fileScanner.nextLine();
                        if (recipeName.contains(word)){                                     //if recipe name contains word, prints recipe
                            System.out.println(recipeName+", cooking time: "+fileScanner.nextLine());
                        }
                    }
                }else if (command.equals("find cooking time")){
                    System.out.print("Max cooking time: ");
                    int time = Integer.valueOf(scanner.nextLine());         
                    System.out.println("Recipes:");
                    String recipeName = fileScanner.nextLine();                     //recipeName of first element
                    int cookingTime = Integer.valueOf(fileScanner.nextLine());      //recipeTime of first element
                    if (cookingTime<=time){                                         //Doing without the loop
                        System.out.println(recipeName+", cooking time: "+cookingTime);
                    }
                    while(fileScanner.hasNextLine()){
                        if (fileScanner.nextLine().isBlank()){                       
                            recipeName = fileScanner.nextLine();                    //If the line is blank, next Line is recipeName and                  
                            cookingTime = Integer.valueOf(fileScanner.nextLine());  //the other line is recipeTime
                            if (cookingTime<=time){
                                System.out.println(recipeName+", cooking time: "+cookingTime);
                            }
                        }
                    }
                }else if (command.equals("find ingredient")){
                    System.out.print("Ingredient: ");
                    String ingredient = scanner.nextLine();                         //For the first element
                    String recipeName = fileScanner.nextLine();                     //For the first element
                    int cookingTime = Integer.valueOf(fileScanner.nextLine());
                    while (fileScanner.hasNextLine()){
                        String line = fileScanner.nextLine();
                        if (line.equals(ingredient)){
                            System.out.println(recipeName+", cooking time: "+cookingTime);
                        }
                        if (line.isEmpty()){
                            recipeName = fileScanner.nextLine();                    //if line is empty next line is recipeName
                            cookingTime = Integer.valueOf(fileScanner.nextLine());  //next line is recipeTime
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

