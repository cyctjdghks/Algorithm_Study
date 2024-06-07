import org.checkerframework.checker.units.qual.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Line implements Comparable<Line>{
    int end;
    int value;

    Line(int end, int value){
        this.value = value;
        this.end = end;
    }

    @Override
    public int compareTo(Line o) {
        return value - o.value;
    }
}

public class Main {
    static int n;
    static int m;
    static int x;
    static ArrayList<ArrayList<Line>> line = new ArrayList<>();
    static ArrayList<ArrayList<Line>> rev_line = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        // 길 정보 저장
        for(int i=0;i<=n;i++){ // 0~n까지
            line.add(new ArrayList<>());
            rev_line.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            line.get(start).add(new Line(end, value));
            rev_line.get(end).add(new Line(start,value));
        }

        // 다익스트라를 사용해서 최단 거리 구하기
        // x에서 집에 돌아가는 경우 : x -> n
        int[] result1 = dijkstra();
        for(int i=1;i<=n;i++){
            System.out.println(i + " : "+result1[i]);
        }

        // x로 가는 경우 : n -> x
        int[] result2 = dijkstra();

        for(int i=1;i<=n;i++){
            System.out.println(i + " : "+result2[i]);
        }

        int maxValue = -1;
        for(int i=1;i<=n;i++){
            if(maxValue < result1[i] + result2[i]){
                maxValue = result1[i] + result2[i];
            }
        }

        System.out.println(maxValue);
    }
    public static int[] dijkstra(){
        int[] dst = new int[n+1];
        Arrays.fill(dst, Integer.MAX_VALUE);

        PriorityQueue<Line> queue = new PriorityQueue<>();
        queue.add(new Line(x, 0));

        boolean[] check = new boolean[n+1];
        // 시작점의 dst를 0으로 설정
        dst[x] = 0;

        while(!queue.isEmpty()){
            int cur = queue.poll().end;

            if(!check[cur]){
                check[cur] = true;

                // 연결된 다리 탐색
                for(Line line : line.get(cur)){
                    if(!check[line.end] && dst[line.end] > dst[cur] + line.value){
                        dst[line.end] = dst[cur] + line.value;
                        queue.add(new Line(line.end, dst[line.end]));
                    }
                }
            }
        }
        return dst;
    }
}
