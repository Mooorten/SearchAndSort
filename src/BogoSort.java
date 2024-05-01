import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class BogoSort {
    public static void bogoSort(int[] list) {
        long startTime = System.nanoTime(); // Start timer
        while (!isSorted(list)) {
            shuffleArray(list);
        }
        long endTime = System.nanoTime(); // End timer
        double durationSeconds = (endTime - startTime) / 1e9; // Convert to seconds
        System.out.println("BogoSort took " + durationSeconds + " seconds.");
    }

    private static boolean isSorted(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i] > list[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static void shuffleArray(int[] list) {
        Random rnd = new Random();
        for (int i = list.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int temp = list[index];
            list[index] = list[i];
            list[i] = temp;
        }
    }

    /** A test method */
    public static void main(String[] args) {
        String filename = "src/randomNumbers"; // Path to your file in src folder
        int[] list = readNumbersFromFile(filename);
        bogoSort(list);
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