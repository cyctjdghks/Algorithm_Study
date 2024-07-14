import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int low=0;
        int high=0;

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            low = Math.max(low,arr[i]);
            high+=arr[i];
        }
        
        while(low<=high){
            int mid = (low+high)/2;
            int sum=0;
            int cnt=0;

            for(int i=0;i<arr.length;i++){
                if(sum+arr[i] > mid){
                    cnt++;
                    sum = arr[i];
                }
                else{
                    sum+=arr[i];
                }

            }
            if(sum != 0) cnt++;

            if(cnt > m){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
        System.out.println(low);

    }
}