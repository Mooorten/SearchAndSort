import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HeapSort {
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is an index in arr[]. n is size of heap
    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    /** A test method */
    public static void main(String[] args) {
        int[] list = readNumbersFromFile("src/randomNumbers.txt");

        long startTime = System.nanoTime(); // Start timer

        heapSort(list);

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