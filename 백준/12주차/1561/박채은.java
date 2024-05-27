import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] arr;
    static int n;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if(n <= m){
            System.out.println(n);
            return ;
        }

        // 여러 개의 놀이기구가 동시에 비어 있으면, 더 작은 번호가 적혀 있는 놀이기구를 먼저 탑승
        // 구하려는 값 : 모든 사람이 놀이기구에 탑승하는 시간
        long left = 0;
        long right = 60000000000L;
        long mid = 0;
        long total = 0;

        while (left <= right){
            mid = (left + right) / 2;
            // 해당 시간 내에 탈 수 있는 아이 수
            total = checkTotalChildren(mid);

            if(total < n){
                left = mid + 1;
            }
            else{
                right = mid -1;
            }
        }

        // mid -1 시간에 몇 명이 탔는지 찾기
        long children = checkTotalChildren(left -1);

        for(int i=0;i<m;i++){
            if(left % arr[i] == 0){ // mid 시간에 탈 수 있는 놀이기구
                children+=1;
            }
            if(children == n){
                System.out.print(i+1);
                break;
            }
        }
    }

    public static long checkTotalChildren(long k){
        long total = m;
        for(int i=0;i< m;i++){
            total += (k / arr[i]);
        }
        return total;
    }
}