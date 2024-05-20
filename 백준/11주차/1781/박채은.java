import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int [][] arr = new int[n][2];
        int result = 0;

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, Comparator.comparingInt(o1 -> o1[0]));

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