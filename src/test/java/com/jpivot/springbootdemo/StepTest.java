package com.jpivot.springbootdemo;

public class StepTest {
    public static void main(String[] args) {
        int[][] obstacleGrid = {{1},{0},{0},{0},{0}};//{{0,0,0},{0,1,0},{0,0,0}};
        int[] aaa = {0,0,0};
        int total = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(total);
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        boolean obstacleFlag = false;
        if(obstacleGrid[0][0]==1){
            return 0;
        }else if(m==1&&n==1&&obstacleGrid[0][0]==0){
            return 1;
        }
        for(int i=0;i<n;i++){
            if(obstacleGrid[0][i]==0&&!obstacleFlag){
                obstacleGrid[0][i] = 1;
            }else{
                obstacleGrid[0][i] = 0;
                obstacleFlag = true;
            }
        }
        obstacleFlag = false;
        for(int i=1;i<m;i++){
            if(obstacleGrid[i][0]==0&&!obstacleFlag){
                obstacleGrid[i][0] = 1;
            }else{
                obstacleGrid[i][0] = 0;
                obstacleFlag=true;
            }
        }
        for(int i = 1;i<m;i++){
            for(int j = 1;j<n;j++){
                if(obstacleGrid[i][j]==1){
                    obstacleGrid[i][j]=0;
                    continue;
                }
                obstacleGrid[i][j]=obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
            }
        }
        return obstacleGrid[m-1][n-1];

    }
}
