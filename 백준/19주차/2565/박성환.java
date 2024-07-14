import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_2565 {

    static int N;
    static ArrayList<Node> list = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            list.add(new Node(start, end));
        }
        // 전깃줄을 시작 위치 기준으로 정렬
        Collections.sort(list, (o1, o2) -> o1.start - o2.start);

        // LIS를 위한 배열
        ArrayList<Integer> lis = new ArrayList<>();

        for (Node node : list) {
            int end = node.end;

            if (lis.isEmpty() || lis.get(lis.size() - 1) < end) {
                lis.add(end);
            } else {
                int pos = binarySearch(lis, end);
                lis.set(pos, end);
            }
        }

        int lisLength = lis.size();
        System.out.println(N - lisLength);
    }

    static int binarySearch(ArrayList<Integer> lis, int key) {
        int left = 0;
        int right = lis.size() - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (lis.get(mid) < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }


    static class Node {
        int start;
        int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
