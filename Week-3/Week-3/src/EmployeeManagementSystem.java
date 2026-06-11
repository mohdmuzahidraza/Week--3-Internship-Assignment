import java.util.*;
import java.io.*;

public class EmployeeManagementSystem {

    static class Employee {
        int    id;
        String name;
        String department;
        double salary;

        Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String toFileString() {
            return id + "," + name + "," + department + "," + salary;
        }

        public void display() {
            System.out.println("ID: " + id + " | Name: " + name +
                    " | Dept: " + department + " | Salary: Rs." + salary);
        }
    }

    static ArrayList<Employee> employeeList = new ArrayList<>();
    static String FILE_NAME = "employees.txt";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadFromFile(); // Load existing data on startup

        int choice;
        System.out.println("========================================");
        System.out.println("     Employee Management System        ");
        System.out.println("========================================");

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Delete Employee by ID");
            System.out.println("5. Save & Exit");
            System.out.print("Enter choice: ");

            choice = getIntInput();

            switch (choice) {
                case 1: addEmployee();       break;
                case 2: viewAllEmployees();  break;
                case 3: searchEmployee();    break;
                case 4: deleteEmployee();    break;
                case 5: saveToFile();
                    System.out.println("✔ Data saved. Goodbye!"); break;
                default: System.out.println("✘ Invalid choice! Enter 1-5.");
            }

        } while (choice != 5);

        sc.close();
    }

    static void addEmployee() {
        System.out.println("\n-- Add New Employee --");

        System.out.print("Enter ID     : ");
        int id = getIntInput();

        for (Employee e : employeeList) {
            if (e.id == id) {
                System.out.println("✘ Employee with ID " + id + " already exists!");
                return;
            }
        }

        sc.nextLine();
        System.out.print("Enter Name   : ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Dept   : ");
        String dept = sc.nextLine().trim();

        System.out.print("Enter Salary : ");
        double salary = getDoubleInput();

        if (salary < 0) {
            System.out.println("✘ Salary cannot be negative!");
            return;
        }

        employeeList.add(new Employee(id, name, dept, salary));
        System.out.println("✔ Employee added successfully!");
    }

    static void viewAllEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        System.out.println("\n-- All Employees --");
        for (Employee e : employeeList) {
            e.display();
        }
    }

    static void searchEmployee() {
        System.out.print("Enter ID to search: ");
        int id = getIntInput();

        for (Employee e : employeeList) {
            if (e.id == id) {
                System.out.println("✔ Found:");
                e.display();
                return;
            }
        }
        System.out.println("✘ Employee with ID " + id + " not found.");
    }

    static void deleteEmployee() {
        System.out.print("Enter ID to delete: ");
        int id = getIntInput();

        Iterator<Employee> it = employeeList.iterator();
        while (it.hasNext()) {
            Employee e = it.next();
            if (e.id == id) {
                it.remove();
                System.out.println("✔ Employee deleted successfully!");
                return;
            }
        }
        System.out.println("✘ Employee with ID " + id + " not found.");
    }

    static void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            for (Employee e : employeeList) {
                writer.write(e.toFileString());
                writer.newLine();
            }
            writer.close();
            System.out.println("✔ Data saved to " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("✘ Error saving file: " + e.getMessage());
        }
    }

    static void loadFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    int    id     = Integer.parseInt(parts[0].trim());
                    String name   = parts[1].trim();
                    String dept   = parts[2].trim();
                    double salary = Double.parseDouble(parts[3].trim());
                    employeeList.add(new Employee(id, name, dept, salary));
                }
            }
            reader.close();
            System.out.println("✔ Previous data loaded from " + FILE_NAME);

        } catch (FileNotFoundException e) {
            System.out.println("ℹ No previous data found. Starting fresh.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("✘ Error loading file: " + e.getMessage());
        }
    }

    static int getIntInput() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("✘ Invalid! Enter a whole number: ");
                sc.next();
            }
        }
    }

    static double getDoubleInput() {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("✘ Invalid! Enter a valid number: ");
                sc.next();

            }
        }
    }
}