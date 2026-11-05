
package a1;

import java.util.Scanner;

public class a1 {

    // Recursive Fibonacci calculation.
    // Time Complexity: O(2^n)
    // Space Complexity: O(n)
    private static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Non-recursive Fibonacci calculation.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static long fibonacciIterative(int n) {
        long previous = 0;
        long current = 1;

        for (int i = 0; i < n; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }

        return previous;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of terms: ");
        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Number of terms must be non-negative.");
            sc.close();
            return;
        }

        // Print Fibonacci series using iterative approach.
        // Overall Time: O(n)
        // Extra Space: O(1)
        System.out.print("Non-recursive Fibonacci series: ");

        long previous = 0;
        long current = 1;

        for (int i = 0; i < n; i++) {
            System.out.print(previous + " ");

            long next = previous + current;
            previous = current;
            current = next;
        }

        System.out.println();

        // Print Fibonacci series using recursive approach.
        // Overall Time: O(2^n)
        // Extra Space: O(n) maximum recursion stack.
        System.out.print("Recursive Fibonacci series: ");

        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciRecursive(i) + " ");
        }

        System.out.println();

        sc.close();
    }
}
