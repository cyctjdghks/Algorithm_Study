import java.util.*;

public class BOJ_13913 {

    static int N, K;
    static int MAX_VALUE = 100000;
    static int res;
    static int[] arr;
    static int[] path;

    public static void main(String[] args) {
        // 수빈이가 있는 위치 N
        // 동생이 있는 위치 K
        // X일 때 걷는다면 1초 후에 X-1 또는 X+1
        // 순간이동을 하는 경우에는 1초 후에 2*X의 위치
        // (0 ≤ N ≤ 100,000)

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        if (N == K) {
            System.out.println(0);
            System.out.println(N);
            return;
        }

        res = MAX_VALUE;

        arr = new int[MAX_VALUE + 1];
        path = new int[MAX_VALUE + 1];

        arr[N] = 0;

        bfs();

        res = arr[K];
        Stack<Integer> stack = new Stack<>();
        stack.push(K);
        int num = K;
        while (num != N) {
            stack.push(path[num]);
            num = path[num];

        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }


        System.out.println(res);
        System.out.println(sb);

    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);

        while (!q.isEmpty()) {
            int idx = q.poll();

            if (idx == K) return;

            if (idx + 1 <= MAX_VALUE && arr[idx + 1] == 0) {
                arr[idx + 1] = arr[idx] + 1;
                path[idx + 1] = idx;
                q.offer(idx + 1);
            }

            if (idx - 1 >= 0 && arr[idx - 1] == 0) {
                arr[idx - 1] = arr[idx] + 1;
                path[idx - 1] = idx;
                q.offer(idx - 1);
            }

            if (idx * 2 <= MAX_VALUE && arr[idx * 2] == 0) {
                arr[idx * 2] = arr[idx] + 1;
                path[idx * 2] = idx;
                q.offer(idx * 2);
            }
        }
    }

}
