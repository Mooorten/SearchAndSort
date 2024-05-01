import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    /** A test method */
    public static void main(String[] args) {
        int[] list = readNumbersFromFile("src/randomNumbers.txt");

        long startTime = System.nanoTime(); // Start timer

        insertionSort(list);

        long endTime = System.nanoTime(); // End timer
        long durationMillis = (endTime - startTime) / 1000000; // Convert to milliseconds
        double durationSeconds = durationMillis / 1000.0; // Convert to seconds

        System.out.println("Sorting took " + durationMillis + " milliseconds.");
        System.out.println("Sorting took " + durationSeconds + " seconds.");

        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
    }

    private static int[] readNumbersFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(" ");
            }
            reader.close();
            String[] numbersString = sb.toString().split(" ");
            int[] numbers = new int[numbersString.length];
            for (int i = 0; i < numbersString.length; i++) {
                numbers[i] = Integer.parseInt(numbersString[i]);
            }
            return numbers;
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            return new int[0];
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
            return new int[0];
        }
    }
}