import java.util.Scanner;

public class Main {
    private static final String menuText = "Enter the number of the function you want to execute:\n1. BMI\n2. Retirement\n3. Exit";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(menuText);
        String choice = scanner.next();
        choice = validateMenuChoice(choice);
        switch (choice){
            case "1":
                //BMI
                getBMIInputs();
                break;
            case "2":
                //Retirement
                getRetirementInputs();
                break;
            case "3":
                System.exit(0);
                break;
        }
    }

    public static String validateMenuChoice(String choice){
        while ((!choice.equals("1"))&&(!choice.equals("2"))&&(!choice.equals("3"))){
            System.out.println("Invalid Choice! Choose again");
            System.out.println(menuText);
            choice = scanner.next();
        }
        return choice;
    }

    public static void getBMIInputs(){
        System.out.println("BMI");
    }

    public static void getRetirementInputs(){
        System.out.println("Retirement");
    }
}
