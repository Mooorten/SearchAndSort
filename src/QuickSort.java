import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuickSort {
    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    private static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    /** Partition the array list[first..last] */
    private static int partition(int[] list, int first, int last) {
        int pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search

        while (high > low) {
            // Search forward from left
            while (low <= high && list[low] <= pivot)
                low++;

            // Search backward from right
            while (low <= high && list[high] > pivot)
                high--;

            // Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot)
            high--;

        // Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }

    /** A test method */
    public static void main(String[] args) {
        int[] list = readNumbersFromFile("src/randomNumbers.txt");

        long startTime = System.nanoTime(); // Start timer

        quickSort(list);

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