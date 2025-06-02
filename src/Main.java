import services.AuthService;
import models.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Time Bank System!");

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Select option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Enter userId: ");
                String userId = scanner.nextLine();

                System.out.print("Enter name: ");
                String name = scanner.nextLine();

                System.out.print("Enter email: ");
                String email = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();


                User newUser = new RegularUser(userId, name, email, password);
                boolean registered = authService.register(newUser);
                if (registered) {
                    System.out.println("Registration successful!");
                }

            } else if (option == 2) {
                System.out.print("Enter email: ");
                String email = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                User loggedInUser = authService.login(email, password);
                if (loggedInUser != null) {
                    loggedInUser.viewDashboard();

                    if (loggedInUser instanceof Admin) {
                        ((Admin) loggedInUser).viewAllUsers(authService.getAllUsers());
                    }
                }

            } else if (option == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
