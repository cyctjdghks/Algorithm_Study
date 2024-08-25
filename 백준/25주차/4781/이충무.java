import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static double m;
    static int t;
    static Candy[] candies;
    static class Candy{
        int cal;
        int price;
        public Candy(int cal,int price){
            this.price = price;
            this.cal=cal;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Double.parseDouble(st.nextToken());

            if(n == 0 && m==0){
                break;
            }
            m+=0.005;
            t = (int)(m*100);
            candies = new Candy[n];
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                int cal = Integer.parseInt(st.nextToken());
                double price = Double.parseDouble(st.nextToken());
                price+=0.005;
                price*=100;

                candies[i] = new Candy(cal,(int)price);
            }

            int result = knapsack();
            System.out.println(result);


        }




    }
    static int knapsack(){
        int[] dp = new int[t+1];
        for(int i=0;i<n;i++){
            for(int j=1;j<=t;j++){
                if(candies[i].price <= j){
                    dp[j] = Math.max(dp[j- candies[i].price]+candies[i].cal, dp[j] );
                }
            }
        }

        return dp[t];
    }

}