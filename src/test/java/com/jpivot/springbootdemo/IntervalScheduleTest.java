package com.jpivot.springbootdemo;

import java.util.Arrays;

/**
 * @author Sherlock
 * 区间调度，最多有几个不相交区间（今天最多参加几个活动）
 */
public class IntervalScheduleTest {
    public static int intervalSchedule(int[][] intvs) {
        if (intvs.length == 0) {
            return 0;
        }
        // 按第二维进行排序
        Arrays.sort(intvs, (o1, o2) -> o1[1] - o2[1]);

        // 至少有一个不相交的区间（一天至少参加一次活动）
        int count = 1;
        // 第一个不相交区间
        int xEnd = intvs[0][1];
        for (int[] interval : intvs) {
            if (interval[0] >= xEnd) {
                count++;
                xEnd = interval[1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] intvs = {{1, 3}, {2, 4}, {3, 6}};
        System.out.printf("最多有：%d个不相交区间", intervalSchedule(intvs));
    }
}
