import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

// LIS(Longest Increasing Subsequence)
// - DP 사용시 시간 복잡도 O(n^2) > 입력 값 크기가 작을 경우 유용
// - 이분 탐색 사용시 시간 복잡도 O(logn) > 정확한 LIS 보다는 LIS의 길이 구할 경우 사용
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] arrIdx = new int[N];

        for(int i=0;i<N;i++) {
            arr[i] = sc.nextInt();
        }

        sc.close();

        list.add(Integer.MIN_VALUE);

        for(int i=0;i<N;i++) {
            int num = arr[i];

            // 수열의 마지막 수보다 큰 경우
            if (num > list.get(list.size() - 1)) {
                list.add(num);
                arrIdx[i] = list.size() - 1;
            } 
            // 수열의 마지막 수보다 작거나 같은 경우 > 수의 범위가 너무 크므로 이분 탐색
            else {
                int left = 0;
                int right = list.size() - 1;

                while (left < right) {
                    int mid = (left + right) / 2;

                    // 수열 중간 값보다 작거나 같은 경우 > 범위 좁히기
                    if (list.get(mid) >= num) right = mid;
                    // 수열 중간 값보다 큰 경우 > 범위 넓히기
                    else left = mid + 1;
                }

                list.set(right, num);
                arrIdx[i] = right;
            }
        }

        // 가장 긴 증가하는 부분 수열의 길이 > 초기값 'Integer.MIN_VALUE' 제거
        sb.append(list.size() - 1 + "\n");

        // 역추적 경로
        Stack<Integer> stack = new Stack<>();

        int idx = list.size() - 1;

        for(int i=N-1;i>=0;i--) {
            if (arrIdx[i] == idx) {
                idx--;
                stack.push(arr[i]);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        System.out.println(sb.toString());
    }
}