import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p,d;
        int [][] arr = new int[n][2];
        int result = 0;

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            arr[i][0] = d;
            arr[i][1] = p;
        }

        Arrays.sort(arr);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<n;i++){
            pq.add(arr[i][1]); // 금액 추가
            int day = arr[i][0];
            while(day < pq.size()){ // 날짜가 부족해지는 경우, 가장 적은 금액대를 빼기
                pq.remove();
            }
        }

        while(!pq.isEmpty()){
            result += pq.poll();
        }
        System.out.println(result);
    }
}