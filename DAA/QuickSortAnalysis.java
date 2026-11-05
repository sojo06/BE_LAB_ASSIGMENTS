package a5;

import java.util.Arrays;
import java.util.Random;

public class QuickSortAnalysis {

    static Random random = new Random();

    // Deterministic Quick Sort
    static void deterministicQuickSort(int[] a, int low, int high) {
        if (low < high) {
            int p = partition(a, low, high, a[high]);
            deterministicQuickSort(a, low, p - 1);
            deterministicQuickSort(a, p + 1, high);
        }
    }

    // Randomized Quick Sort
    static void randomizedQuickSort(int[] a, int low, int high) {
        if (low < high) {
            int pivotIndex = low + random.nextInt(high - low + 1);

            // Move randomly selected pivot to the end
            swap(a, pivotIndex, high);

            int p = partition(a, low, high, a[high]);

            randomizedQuickSort(a, low, p - 1);
            randomizedQuickSort(a, p + 1, high);
        }
    }

    static int partition(int[] a, int low, int high, int pivot) {
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }

        swap(a, i + 1, high);
        return i + 1;
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] original = {45, 23, 67, 12, 89, 34, 10, 56};

        int[] a = original.clone();
        int[] b = original.clone();

        long start = System.nanoTime();
        deterministicQuickSort(a, 0, a.length - 1);
        long deterministicTime = System.nanoTime() - start;

        start = System.nanoTime();
        randomizedQuickSort(b, 0, b.length - 1);
        long randomizedTime = System.nanoTime() - start;

        System.out.println("Original Array: " + Arrays.toString(original));
        System.out.println("Deterministic Quick Sort: " + Arrays.toString(a));
        System.out.println("Randomized Quick Sort: " + Arrays.toString(b));

        System.out.println("Deterministic Time: "
                + deterministicTime + " ns");
        System.out.println("Randomized Time: "
                + randomizedTime + " ns");
    }
}
