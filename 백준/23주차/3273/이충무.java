import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static int x;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        arr= new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int cnt =0;

        int start = 0;
        int end = n-1;

        while(start < end){
            int sum = arr[start] + arr[end];

            if(sum == x){
                cnt++;
                start++;
            }
            else if(sum > x){
                end--;
            }
            else if(sum < x){
                start++;
            }


        }

        System.out.println(cnt);



    }






}