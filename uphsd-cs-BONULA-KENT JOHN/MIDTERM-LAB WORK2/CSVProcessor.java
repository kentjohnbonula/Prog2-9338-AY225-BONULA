
/**
 * =====================================================
 * Student Name    : BOÑULA, KENT JOHN C.
 * Course          : BSCSIT 1203 Programming 2
 * Assigned MPs    : MP02, MP11, MP18
 * Description     : Processes a CSV dataset to show the first 10 rows,
 * count exam frequencies, and remove rows with missing data.
 * =====================================================
 */

import java.io.*;
import java.util.*;

public class CSVProcessor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt user for dataset file path
        System.out.print("Enter the full path of the CSV dataset: ");
        String filePath = sc.nextLine();

        List<String[]> validRecords = new ArrayList<>();
        Map<String, Integer> examFrequency = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Split line by comma while handling quotes
                String[] columns = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // MP18: Remove rows with empty fields
                boolean isComplete = true;
                for (String col : columns) {
                    if (col == null || col.trim().isEmpty()) {
                        isComplete = false;
                        break;
                    }
                }

                // If row is valid (not empty and has enough columns)
                if (isComplete && columns.length >= 4) {
                    validRecords.add(columns);

                    // MP11: Frequency count for "Exam" column (Index 3 based on your sample)
                    String exam = columns[3].trim();
                    examFrequency.put(exam, examFrequency.getOrDefault(exam, 0) + 1);
                }
            }

            // MP02: Display first 10 rows
            System.out.println("\n--- MP02: FIRST 10 VALID ROWS ---");
            for (int i = 0; i < Math.min(10, validRecords.size()); i++) {
                System.out.println(String.join(" | ", validRecords.get(i)));
            }

            // MP11: Display Frequency Count
            System.out.println("\n--- MP11: EXAM FREQUENCY COUNT ---");
            for (Map.Entry<String, Integer> entry : examFrequency.entrySet()) {
                System.out.printf("%-30s : %d\n", entry.getKey(), entry.getValue());
            }

            System.out.println("\n--- MP18: Empty rows have been excluded from this processing. ---");

        } catch (IOException e) {
            System.out.println("Error: Could not find or read the file. Please check the path.");
        }
    }
}
