import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
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

        HashSet<Integer> set = new HashSet<>();

        long cnt =0;
        int start = 0;
        for(int i=0;i<n;i++){
            if(set.contains(arr[i])){
                for(int j=start;j<i;j++) {
                    cnt +=i-j;
                    start++;
                    set.remove(arr[j]);
                    if(arr[j] == arr[i]) break;
                }
            }
            set.add(arr[i]);

        }
        for(int i=start;i<n;i++){
            cnt+=n-i;
        }
        System.out.println(cnt);



    }






}