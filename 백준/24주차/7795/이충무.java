import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m,size;
    static int[] a;
    static int[] b;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        size = Integer.parseInt(br.readLine());
        for(int k=0;k<size;k++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            a = new int[n];
            b= new int[m];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                a[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<m;i++){
                b[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(a);
            Arrays.sort(b);

            System.out.println(countFunc());
        }


    }
    static int countFunc(){
        int cnt =0;

        for(int i=0;i<n;i++){
            int cur = a[i];
            int start = 0;
            int end = m-1;


            while(start<=end){
                int mid = (start+end)/2;
                if(b[mid] < cur){
                    start = mid + 1;
                }
                else{
                    end = mid -1;
                }
            }
            cnt+=start;
        }
        return cnt;
    }






}