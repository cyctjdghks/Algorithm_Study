import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[] arr= new int[n];
        st= new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] record = new int[n];
        int[] lis =new int[n+1];
        int len = 0;
        lis[0]=arr[0];
        for(int i=1;i<n;i++){
            if(arr[i] > lis[len]){
                lis[len+1]=arr[i];
                len+=1;
                record[i] = len;
            }else{
                int idx = binarySearch(lis,0,len,arr[i]);
                lis[idx]=arr[i];
                record[i] = idx;
            }
        }

        System.out.println(len+1);


        int[] result= new int[len+1];
        int idx = len;
        for(int i=n-1;i>=0;i--){
            if(record[i] == idx){
                result[idx--]=arr[i];
            }
        }

        for(int i=0;i<=len;i++){
            System.out.print(result[i] + " ");
        }


    }
    static int binarySearch(int[] lis, int left, int right, int target) {
        int mid;

        while (left < right) {
            mid = (left + right) / 2;

            if (lis[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        return right;
    }
}