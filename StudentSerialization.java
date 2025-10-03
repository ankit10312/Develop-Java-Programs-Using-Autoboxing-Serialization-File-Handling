import java.io.*;
import java.util.Scanner;

// Student class
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}

public class StudentSerialization {
    private static final String FILE_NAME = "student.ser";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Serialization Menu ---");
            System.out.println("1. Serialize Student");
            System.out.println("2. Deserialize Student");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> serializeStudent();
                case 2 -> deserializeStudent();
                case 3 -> { System.out.println("Exiting..."); System.exit(0); }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void serializeStudent() {
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Student Age: ");
        int age = sc.nextInt();

        Student s = new Student(name, age);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(s);
            System.out.println("Student serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserializeStudent() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Student s = (Student) ois.readObject();
            System.out.println("Deserialized Student: " + s);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No serialized student found or error reading file.");
        }
    }
}
