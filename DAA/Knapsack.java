package a3;
import java.util.*;

public class Knapsack {

    // ================= DYNAMIC PROGRAMMING =================

    // Time: O(n * W)
    // Space: O(n * W)
    static int knapsackDP(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {

                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        dp[i - 1][w],
                        val[i - 1] + dp[i - 1][w - wt[i - 1]]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }

    // ================= BRANCH AND BOUND =================

    static class Item {
        int weight, profit;
        double ratio;

        Item(int weight, int profit) {
            this.weight = weight;
            this.profit = profit;
            this.ratio = (double) profit / weight;
        }
    }

    static class Node {
        int level, weight, profit;
        double bound;

        Node(int level, int weight, int profit) {
            this.level = level;
            this.weight = weight;
            this.profit = profit;
        }
    }

    static double bound(Node u, int n, int W, Item[] items) {

        if (u.weight >= W) {
            return u.profit;
        }

        double profitBound = u.profit;
        int totalWeight = u.weight;
        int j = u.level + 1;

        while (j < n && totalWeight + items[j].weight <= W) {
            totalWeight += items[j].weight;
            profitBound += items[j].profit;
            j++;
        }

        if (j < n) {
            profitBound += (W - totalWeight) * items[j].ratio;
        }

        return profitBound;
    }

    // Time: O(2^n) worst case
    // Space: O(2^n) worst case
    static int knapsackBB(Item[] items, int W) {

        int n = items.length;

        Arrays.sort(items, (a, b) ->
            Double.compare(b.ratio, a.ratio)
        );

        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b.bound, a.bound)
        );

        Node root = new Node(-1, 0, 0);
        root.bound = bound(root, n, W, items);

        pq.add(root);

        int maxProfit = 0;

        while (!pq.isEmpty()) {

            Node u = pq.poll();

            if (u.bound <= maxProfit) {
                continue;
            }

            int nextLevel = u.level + 1;

            if (nextLevel >= n) {
                continue;
            }

            // Include current item
            Node include = new Node(
                nextLevel,
                u.weight + items[nextLevel].weight,
                u.profit + items[nextLevel].profit
            );

            if (include.weight <= W) {

                maxProfit = Math.max(maxProfit, include.profit);

                include.bound = bound(include, n, W, items);

                if (include.bound > maxProfit) {
                    pq.add(include);
                }
            }

            // Exclude current item
            Node exclude = new Node(
                nextLevel,
                u.weight,
                u.profit
            );

            exclude.bound = bound(exclude, n, W, items);

            if (exclude.bound > maxProfit) {
                pq.add(exclude);
            }
        }

        return maxProfit;
    }

    // ================= MAIN METHOD =================

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] wt = new int[n];
        int[] val = new int[n];
        Item[] items = new Item[n];

        System.out.println("Enter weights:");
        for (int i = 0; i < n; i++) {
            wt[i] = sc.nextInt();
        }

        System.out.println("Enter profits:");
        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
            items[i] = new Item(wt[i], val[i]);
        }

        System.out.print("Enter knapsack capacity: ");
        int W = sc.nextInt();

        System.out.println("\n--- Results ---");

        int dpResult = knapsackDP(wt, val, W);
        System.out.println("Dynamic Programming: " + dpResult);

        int bbResult = knapsackBB(items, W);
        System.out.println("Branch and Bound: " + bbResult);

        sc.close();
    }
}
