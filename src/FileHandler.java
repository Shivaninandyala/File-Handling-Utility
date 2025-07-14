import java.io.*;
import java.util.*;

public class FileHandler {

    private static final String FILE_NAME = "intern_data.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== CODTECH FILE HANDLING UTILITY =====");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Modify File Content");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    writeToFile(scanner);
                    break;
                case 2:
                    readFromFile();
                    break;
                case 3:
                    modifyFile(scanner);
                    break;
                case 4:
                    System.out.println("Exiting... Completion Certificate will be issued on your internship end date.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    // Function to write data to the file
    private static void writeToFile(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            System.out.println("Enter text to write (type 'exit' on a new line to stop):");
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) break;
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Function to read and display file contents
    private static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            System.out.println("\n=== File Contents ===");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please write to the file first.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Function to modify file content by replacing a word
    private static void modifyFile(Scanner scanner) {
        System.out.print("Enter word to replace: ");
        String oldWord = scanner.nextLine();

        System.out.print("Enter new word: ");
        String newWord = scanner.nextLine();

        File file = new File(FILE_NAME);
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                fileContent.append(line.replaceAll(oldWord, newWord)).append("\n");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(fileContent.toString());
                System.out.println("File modified successfully.");
            }

        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }
}
