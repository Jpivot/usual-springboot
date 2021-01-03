package com.jpivot.springbootdemo;

public class ZeroOnePathTest {

    public static void main(String[] args) {
        int[] wt = {15, 16, 20, 12, 9, 14, 18};
        int[] value = {32, 37, 46, 26, 21, 30, 42};
        int W = 60;
        System.out.println(caculate(wt, value, W));

    }


    public static int caculate(int[] wt, int[] value, int bagWeight) {
        /**
         * 从1开始计数，考虑的是当前背包w的重量只会从1开始，不会是0
         * 另外，要考虑上一种状态，i-1
         */
        int n = value.length;
        int[][] dp = new int[n + 1][bagWeight + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= bagWeight; w++) {
                // 这里考虑w比单个物品的重量还小的情形
                if (w < wt[i - 1]) {
                    // w表示的是当前背包的容量
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 择优
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - wt[i - 1]] + value[i - 1]);
                }
                System.out.print(dp[i][w]+" ");
            }
            System.out.println("\r\n");
        }

        // 这里再求出装入的都是哪几个物品
        return dp[n][bagWeight];
    }
}
