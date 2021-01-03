package com.jpivot.springbootdemo;

public class UniquePathsTest {
    public static int uniquePaths(int m,int n){
        int f[][] = new int[m][n];
        for(int i=0;i<m;i++){
            f[i][0] = 1;
        }
        for (int j=0;j<n;j++){
            f[0][j]=1;
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                f[i][j]=f[i-1][j]+f[i][j-1];
            }
        }
        return f[m-1][n-1];
    }

    public static void main(String[] args) {
        int uniquePaths = uniquePaths(20,20);
        System.out.println(uniquePaths);
    }
}
