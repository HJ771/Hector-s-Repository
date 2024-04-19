import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ExpenseTrack {
    private static List<Expense> expenses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    private static class Expense {
        String description;
        double amount;
        String category;

        public Expense(String description, double amount, String category) {
            this.description = description;
            this.amount = amount;
            this.category = category;
        }

        @Override
        public String toString() {
            return String.format("%s: $%.2f (Category: %s)", description, amount, category);
        }
    }

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("\n1. Add Expense");
            System.out.println("2. List Expenses");
            System.out.println("3. Show Total Expense");
            System.out.println("4. Save Expenses");
            System.out.println("5. Exit");
            System.out.print("Coge una de esas: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    listExpenses();
                    break;
                case 3:
                    showTotal();
                    break;
                case 4:
                    saveExpenses();
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid option, please choose again.");
            }
        } while (option != 5);

        scanner.close();
    }

    private static void addExpense() {
        System.out.print("Enter Description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        expenses.add(new Expense(desc, amount, category));
        System.out.println("Expense Added.");
    }

    private static void listExpenses() {
        System.out.println("\nExpenses:");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    private static void showTotal() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.amount;
        }
        System.out.printf("Total Expenses: $%.2f\n", total);
    }

    private static void saveExpenses() {
        try (PrintWriter out = new PrintWriter(new FileWriter("expenses.txt"))) {
            for (Expense expense : expenses) {
                out.printf("%s,%.2f,%s%n", expense.description, expense.amount, expense.category);
            }
            System.out.println("Expenses saved to 'expenses.txt'");
        } catch (IOException e) {
            System.out.println("Failed to save expenses: " + e.getMessage());
        }
    }
}
