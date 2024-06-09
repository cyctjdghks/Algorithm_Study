import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1238 {

    static int N, M, X;
    static ArrayList<Data>[] list;
    static int[] dist;
    static int res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        X = sc.nextInt();
        list = new ArrayList[N + 1];
        res = 0;

        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<Data>();
        }

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int t = sc.nextInt();

            list[x].add(new Data(y, t));
        }

        for (int i = 1; i <= N; i++) {
            if (i == X)
                continue;
            int go = bfs(i, X); // 자기 마을에서 X 마을로
            int back = bfs(X, i); // 자기 마을에서 X 마을로

            res = Math.max(res, go + back);
        }

        System.out.println(res);
    }


    private static int bfs(int start, int end) {
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Data> q = new LinkedList<Data>();
        q.offer(new Data(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()) {
            Data data = q.poll();

            for (int i = 0; i < list[data.y].size(); i++) {
                if (data.T + list[data.y].get(i).T < dist[list[data.y].get(i).y]) {
                    dist[list[data.y].get(i).y] = data.T + list[data.y].get(i).T;
                    q.offer(new Data(list[data.y].get(i).y, data.T + list[data.y].get(i).T));
                }
            }
        }


        return dist[end];
    }

    static class Data {
        int y;
        int T;

        public Data(int y, int t) {
            super();
            this.y = y;
            T = t;
        }
    }

}