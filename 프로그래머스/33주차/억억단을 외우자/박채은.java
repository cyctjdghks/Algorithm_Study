import java.util.*;
class Solution {
    class Point{
        int n;
        int k;
        Point(int n, int k){
            this.n = n;
            this.k = k;
        }
    }

    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        // 1~e 까지의 약수 개수를 구함
        Point[] dp = new Point[e];
        for(int i=1;i<=e;i++){
            dp[i-1] = new Point(i, 1);
        }

        for (int i = 2; i <= e; i++)
            for (int j = 1; j <= e / i; j++)
                dp[i * j - 1].k++;

        Arrays.sort(dp, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.k < o2.k) return 1;
                else if(o1.k > o2.k) return -1;
                else{
                    if(o1.n > o2.n) return 1;
                    else if (o1.n < o2.n) {
                        return -1;
                    }
                    else return 0;
                }
            }
        });

        for(int i=0;i<starts.length;i++){
            for(int j=0;j<e;j++){
                if(dp[j].n >= starts[i]){ // start[i] <= n <= e
                    answer[i] = dp[j].n;
                    break;
                }
            }
        }

        return answer;
    }
}