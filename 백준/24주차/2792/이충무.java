import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m,size;
    static int[] a;
    static int[] b;
    static int[] arr;
    static int minVal = 300000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr= new int[m];

        int start = 1;
        int end = 0;
        for(int i=0;i<m;i++){
            arr[i] = Integer.parseInt(br.readLine());
            end = Math.max(end, arr[i]);
        }


        while(start<=end){
            int mid = (start+end)/2;
            int sum = 0;
            for(int i=0;i<m;i++){
                if(arr[i]%mid == 0){
                    sum+=arr[i]/mid;
                }
                else{
                    sum+=arr[i]/mid + 1;
                }
            }

            if(sum > n){
                start = mid+1;
            }
            else{
                end = mid -1;
                minVal = mid;
            }

        }

        System.out.println(minVal);



    }
}