import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] arr;
    static int n;
    static int s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        int max = 0;
        long sum = 0;

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            sum+=arr[i];
        }

        int left = 1;
        int right = 1_000_000_000;
        while(left <= right){
            int mid = (left + right) / 2;
            if(count(mid) >= s){ // 넣을 수 있는 파가 남는다. 같은 경우에도 더 길어질 수 있기 때문에 max를 찾을 때까지 돌기
                max = mid;
                left = mid + 1;
            }
            else{
                right = mid -1;
            }
        }

        System.out.println(sum - (max * (long) s));
    }
    // 해당 파의 길이로 얼마나 많은 파닭에 넣을 수 있는지 cnt
    public static int count(int pa){
        int cnt = 0;
        for(int i=0;i<arr.length;i++){
            int k = arr[i] / pa;
            cnt+= k;
        }
        return cnt;
    }
}