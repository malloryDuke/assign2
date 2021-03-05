import java.util.Scanner;

public class Main {
    private static final String menuText = "Enter the number of the function you want to execute:\n1. BMI\n2. Retirement\n3. Exit";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String choice = displayMenu();
        while (!choice.equals("3")) {
            //BMI
            //Retirement
            switch (choice) {
                case "1" -> {
                    System.out.print(bmi());
                    choice = displayMenu();
                }
                case "2" -> {
                    String ageWhenMet = retirement();
                    System.out.println(ageWhenMet);
                    choice = displayMenu();
                }
            }
        }
        System.exit(0);
    }

    private static String displayMenu() {
        System.out.println(menuText);
        String choice = scanner.next();
        choice = validateMenuChoice(choice);
        return choice;
    }

    private static String validateMenuChoice(String choice) {
        while ((!choice.equals("1")) && (!choice.equals("2")) && (!choice.equals("3"))) {
            System.out.println("Invalid Choice! Choose again");
            System.out.println(menuText);
            choice = scanner.next();
        }
        return choice;
    }

    public static String bmi() {
        System.out.print("Enter height - Feet: ");
        int feet = scanner.nextInt();
        while (feet < 0) {
            System.out.print("Invalid input! Feet cannot be negative");
            System.out.print("\nEnter height - Feet: ");
            feet = scanner.nextInt();
        }
        System.out.print("Enter height - Inches: ");
        int inches = scanner.nextInt();
        while (inches < 0) {
            System.out.print("Invalid input! Inches cannot be negative");
            System.out.print("\nEnter height - Inches: ");
            inches = scanner.nextInt();
        }
        System.out.print("Enter weight (pounds): ");
        float weight = scanner.nextFloat();
        while (weight < 0) {
            System.out.print("Invalid input! Weight cannot be negative");
            System.out.print("\nEnter weight (pounds): ");
            weight = scanner.nextFloat();
        }
        float bmi = calcBMI(feet, inches, weight);
        String category = getBMICategory(bmi);
        return "Your BMI is " + bmi + " and your category is " + category + "\n";
    }

    public static String retirement() {
        System.out.print("Enter your current age: ");
        int age = scanner.nextInt();
        while ((age < 0) || (age >= 100)) {
            System.out.print("Invalid input! Age cannot be negative or >= 100");
            System.out.print("\nEnter your current age: ");
            age = scanner.nextInt();
        }
        System.out.print("Enter your annual salary: ");
        double salary = scanner.nextDouble();
        while (salary <= 0) {
            System.out.print("Invalid input! Salary cannot be negative or 0");
            System.out.print("\nEnter your annual salary: ");
            salary = scanner.nextDouble();
        }
        System.out.print("Enter the percent saved (Ex. if you save 10% enter 0.10): ");
        double percentSave = scanner.nextDouble();
        while (percentSave <= 0) {
            System.out.print("Invalid input! Percent saved cannot be negative or 0");
            System.out.print("\nEnter percent saved (Ex. if you save 10% enter 0.10): ");
            percentSave = scanner.nextDouble();
        }
        System.out.print("Enter your desired retirement savings goal: ");
        double savingsGoal = scanner.nextDouble();
        while (savingsGoal < 0) {
            System.out.print("Invalid input! Savings goal cannot be negative");
            System.out.print("\nEnter your desired retirement savings goal: ");
            savingsGoal = scanner.nextDouble();
        }
        int ageWhenMet = ageWhenMet(age, salary, percentSave, savingsGoal);
        boolean isGoalMet = isGoalMet(ageWhenMet);
        if (isGoalMet) {
            return "Goal is met at age " + ageWhenMet;
        } else {
            return "Goal is not met! Your age when met = " + ageWhenMet;
        }
    }

    public static float calcBMI(int feet, int inches, float weight) {
        int totalInches = (feet * 12) + inches;
        double convertedWeight = weight * 0.45;
        double convertedHeight = totalInches * 0.025;
        double convHeightSqr = convertedHeight * convertedHeight;
        return (float) (convertedWeight / convHeightSqr);
    }

    public static String getBMICategory(double BMI) {
        String category;
        if (BMI < 18.5) {
            category = "Underweight";
        } else if (BMI < 25) {
            category = "Normal weight";
        } else if (BMI < 30) {
            category = "Overweight";
        } else {
            category = "Obese";
        }
        return category;
    }

    public static float calcSavingsPerYr(double salary, double percentSave) {
        return (float) ((salary * percentSave) * 1.35);
    }

    public static double yrsTillGoal(double savingsGoal, double savingsPerYr) {
        return Math.ceil(savingsGoal / savingsPerYr);
    }

    public static int ageWhenMet(int age, double salary, double percentSave, double savingsGoal) {
        double savingsPerYr = calcSavingsPerYr(salary, percentSave);
        double yrsTillGoal = yrsTillGoal(savingsGoal, savingsPerYr);
        return (int) (age + yrsTillGoal);
    }

    public static boolean isGoalMet(int ageWhenMet) {
        return ageWhenMet < 100;
    }
}
