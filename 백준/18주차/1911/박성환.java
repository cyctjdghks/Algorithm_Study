import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1911 {

    static int N, L;
    static int res;

    public static void main(String[] args) {
        // N 개의 물웅덩이
        // 길이가 L 인 널빤지

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        res = 0; // 널빤지 갯수
        int idx = 0; // 널빤지를 넢은 위치

        PriorityQueue<Puddle> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            pq.offer(new Puddle(start, end));
        }

        while (!pq.isEmpty()) {
            Puddle puddle = pq.poll();

            if (puddle.end < idx) continue;

            if (idx < puddle.start) idx = puddle.start;

            int num = (puddle.end - idx) % L;
            res += (puddle.end - idx) / L;
            idx = puddle.end;

            if (num != 0) {
                res++;
                idx += L - num;
            }
        }

        System.out.println(res);
    }

    static class Puddle implements Comparable<Puddle> {
        int start;
        int end;

        public Puddle(int start, int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        public int compareTo(Puddle o) {
            return this.start - o.start;
        }
    }
}
