import java.util.*;
class Solution {
    public int[][] cost = {
            { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
            { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
            { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
            { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
            { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
            { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
            { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
            { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
            { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
            { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };
    public int[][][] dp;
    public String arr;
    public int len;

    public int DP(int index, int left, int right){
        if(index == len){
            return 0;
        }
        if(dp[index][left][right] != -1){
            return dp[index][left][right];
        }

        int num = arr.charAt(index) - '0';
        int result = Integer.MAX_VALUE;

        // 왼쪽 손
        if(num!=right){
            result = Math.min(DP(index+1, num, right) + cost[left][num], result);
        }

        // 오른쪽 손
        if(num!=left){
            result = Math.min(DP(index+1, left, num) + cost[right][num], result);
        }
        return dp[index][left][right] = result;
    }

    public int solution(String numbers) {
        arr = numbers;
        len = numbers.length();
        dp = new int[len+1][10][10];

        for(int i=0;i< len+1;i++){
            for(int j=0;j<10;j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        return DP(0, 4, 6);
    }
}