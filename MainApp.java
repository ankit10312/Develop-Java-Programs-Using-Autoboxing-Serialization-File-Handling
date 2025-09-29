import java.io.*;
import java.util.*;

// -------------------- Part B: Student Class (Serializable) --------------------
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    int studentID;
    String name;
    String grade;

    public Student(int studentID, String name, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    public String toString() {
        return "StudentID: " + studentID + ", Name: " + name + ", Grade: " + grade;
    }
}

// -------------------- Part C: Employee Class --------------------
class Employee {
    String name;
    int id;
    String designation;
    double salary;

    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
    }
}

// -------------------- Main Application --------------------
public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // -------------------- Part A: Autoboxing and Unboxing --------------------
        System.out.println("=== Part A: Sum of Integers Using Autoboxing and Unboxing ===");
        System.out.print("Enter integers separated by space: ");
        String inputLine = sc.nextLine();
        String[] inputs = inputLine.split(" ");

        ArrayList<Integer> numbers = new ArrayList<>();
        for (String s : inputs) {
            Integer num = Integer.parseInt(s); // parsing string to Integer (Autoboxing)
            numbers.add(num);
        }

        int sum = 0;
        for (Integer n : numbers) {
            sum += n; // unboxing happens automatically
        }
        System.out.println("Sum of integers: " + sum);

        // -------------------- Part B: Serialization/Deserialization --------------------
        System.out.println("\n=== Part B: Serialization and Deserialization ===");
        Student st = new Student(101, "Ankit Raj", "A");
        String filename = "student.ser";

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(st);
            System.out.println("Student object serialized to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Student deserializedStudent = (Student) ois.readObject();
            System.out.println("Deserialized Student: " + deserializedStudent);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // -------------------- Part C: Menu-Based Employee Management --------------------
        System.out.println("\n=== Part C: Employee Management System ===");
        String empFile = "employees.txt";
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Employee Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Employee Designation: ");
                    String desig = sc.nextLine();
                    System.out.print("Enter Employee Salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();

                    Employee emp = new Employee(name, id, desig, salary);

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(empFile, true))) {
                        bw.write(emp.toString());
                        bw.newLine();
                        System.out.println("Employee added successfully!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println("Employee Records:");
                    try (BufferedReader br = new BufferedReader(new FileReader(empFile))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.out.println("No employees found or error reading file.");
                    }
                    break;

                case 3:
                    running = false;
                    System.out.println("Exiting Employee Management System...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
