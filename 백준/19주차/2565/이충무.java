import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Point{
    int a,b;
    public Point(int a, int b) {
        super();
        this.a = a;
        this.b = b;
    }

}
public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        Point[] arr = new Point[n];
        for(int i=0;i<n;i++){
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i] = new Point(a,b);
        }

        Arrays.sort(arr, Comparator.comparingInt(x -> x.a));

        int[] dp = new int[n];
        int max = 0;
        for(int i=0;i<n;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(arr[i].b > arr[j].b){
                    dp[i] = Math.max(dp[i], dp[j]+1);    
                }
                
            }
            max= Math.max(max,dp[i]);
        }
        System.out.println(n-max);

    }
}