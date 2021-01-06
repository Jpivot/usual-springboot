package com.jpivot.springbootdemo;

public class ZeroOnePathTest {

    public static void main(String[] args) {
//        int[] wt = {15, 16, 2, 12, 9, 14, 18};
        int[] wt = {4, 6, 5, 2, 7, 3};
//        int[] value = {9, 37, 46, 26, 21, 30, 42};
        int[] value = {2, 10, 3, 6, 5, 4};
        int W = 17;
        System.out.println(caculate(wt, value, W));
    }

    /**
     * 顺推方式
     *
     * @param wt
     * @param value
     * @param bagWeight
     * @return
     */
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
//                System.out.print(dp[i][w]+" ");
            }
//            System.out.println("\r\n");
        }
        // 当前放入的物品的总重量
        int curTotal = 0;
        // 当前物品的价值
        int curWorth = 0;
        // 当前背包的重量
        int curBagWeight = bagWeight;
        for (int i = n; i >= 1; i--) {
            if (dp[i][curBagWeight] - dp[i - 1][curBagWeight] > 0) {
                curBagWeight = curBagWeight - wt[i - 1];
                curTotal = curTotal + wt[i - 1];
                curWorth = curWorth + value[i - 1];
                System.out.printf("第%d件    %2d    %3d\r\n", i, wt[i - 1], value[i - 1]);
            }
        }
//        if (dp[n][bagWeight] - curWorth == value[0]) {
//            curTotal = curTotal + wt[0];
//            curWorth = curWorth + value[0];
//            System.out.printf("第%d件    %2d    %3d\r\n", 1, wt[0], value[0]);
//        }
        System.out.printf("当前总重量：%2d,当前总价值：%3d\r\n", curTotal, curWorth);

        // 这里再求出装入的都是哪几个物品
        return dp[n][bagWeight];
    }
}
