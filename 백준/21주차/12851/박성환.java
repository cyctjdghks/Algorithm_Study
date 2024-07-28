import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_12851 {

    static int N, K;
    static int[] arr;
    static int res, cnt;

    public static void main(String[] args) {
        // N => K

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        arr = new int[100001];
        Arrays.fill(arr, Integer.MAX_VALUE);

        res = Integer.MAX_VALUE;
        cnt = 0;

        bfs();

        System.out.println(res + " " + cnt);
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(N, 0));
        arr[N] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            int index = node.index;
            int count = node.count;

            if (index == K && res >= count) {
                cnt++;
                res = Math.min(res, count);
                continue;
            }

            if (count > res) {
                continue;
            }

            if (isInBound(index + 1) && arr[index + 1] >= count) {
                q.offer(new Node(index + 1, count + 1));
                arr[index + 1] = count;
            }
            if (isInBound(index - 1) && arr[index - 1] >= count) {
                q.offer(new Node(index - 1, count + 1));
                arr[index - 1] = count;
            }
            if (isInBound(index * 2) && arr[index * 2] >= count) {
                q.offer(new Node(index * 2, count + 1));
                arr[index * 2] = count;
            }
        }
    }

    static boolean isInBound(int x) {
        return x >= 0 && x <= 100000;
    }

    static class Node {
        int index;
        int count;

        public Node(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }
}
