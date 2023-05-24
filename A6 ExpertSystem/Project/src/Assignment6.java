import java.sql.*;
import java.util.Scanner;

public class Assignment6
 {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();

            System.out.println("Welcome to the Student Management System!");

            while (true) {
                System.out.println("\nMENU:");
                System.out.println("1. Add a student");
                System.out.println("2. Edit a student");
                System.out.println("3. Delete a student");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        addStudent(statement, scanner);
                        break;
                    case 2:
                        editStudent(statement, scanner);
                        break;
                    case 3:
                        deleteStudent(statement, scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addStudent(Statement statement, Scanner scanner) throws SQLException {
        System.out.println("\nADD STUDENT");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter student class: ");
        String studentClass = scanner.nextLine();

        String query = "INSERT INTO students (name, age, class) VALUES ('" + name + "', " + age + ", '" + studentClass + "')";
        int rowsAffected = statement.executeUpdate(query);
        if (rowsAffected > 0) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Failed to add student.");
        }
    }

    private static void editStudent(Statement statement, Scanner scanner) throws SQLException {
        System.out.println("\nEDIT STUDENT");
        System.out.print("Enter student ID to edit: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.println("Select field to edit:");
        System.out.println("1. Name");
        System.out.println("2. Age");
        System.out.println("3. Class");
        System.out.print("Enter your choice: ");
        int fieldChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        String field;
        switch (fieldChoice) {
            case 1:
                field = "name";
                break;
            case 2:
                field = "age";
                break;
            case 3:
                field = "class";
                break;
            default:
                System.out.println("Invalid choice. No changes made.");
                return;
        }

        System.out.print("Enter new value: ");
        String newValue = scanner.nextLine();

        String query = "UPDATE students SET " + field + " = '" + newValue + "' WHERE id = " + studentId;
        int rowsAffected = statement.executeUpdate(query);
        if (rowsAffected > 0) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Failed to update student. Please check student ID.");
        }
    }

    private static void deleteStudent(Statement statement, Scanner scanner) throws SQLException {
        System.out.println("\nDELETE STUDENT");
        System.out.print("Enter student ID to delete: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        String query = "DELETE FROM students WHERE id = " + studentId;
        int rowsAffected = statement.executeUpdate(query);
        if (rowsAffected > 0) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Failed to delete student. Please check student ID.");
        }
    }
}
