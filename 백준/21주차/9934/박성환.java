import java.sql.Statement;
import java.util.*;

public class BOJ_9934 {

    static int K;
    static int[] arr;
    static Node root;

    public static void main(String[] args) {
        // => 중위 순회

        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();

        K = sc.nextInt();
        int size = (int) Math.pow(2, K);
        arr = new int[size];
        for (int i = 1; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        root = new Node(arr[(1 + size) / 2]);
        createNode(1, size, 1, root);

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int depth = q.size();

            for (int i = 0; i < depth; i++) {
                Node node = q.poll();

                sb.append(node.idx).append(" ");
                Optional.ofNullable(node.left).ifPresent(q::add);
                Optional.ofNullable(node.right).ifPresent(q::add);
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void createNode(int start, int end, int dep, Node node) {
        if (dep == K) {
            return;
        }

        int mid = (start + end) / 2;

        // left
        node.left = new Node(arr[(start + mid) / 2]);
        createNode(start, mid, dep + 1, node.left);

        // right
        node.right = new Node(arr[(mid + end) / 2]);
        createNode(mid, end, dep + 1, node.right);
    }

    static class Node {
        int idx;
        Node left;
        Node right;

        public Node(int idx) {
            this.idx = idx;
            this.left = null;
            this.right = null;
        }

        public Node(int idx, Node left) {
            this.idx = idx;
            this.left = left;
            this.right = null;
        }

        public Node(int idx, Node left, Node right) {
            this.idx = idx;
            this.left = left;
            this.right = right;
        }
    }
}
