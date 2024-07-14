import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BOJ_14003 {

    static int N;
    static int[] arr;
    static int[] indexArr;
    static List<Integer> list;
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N + 1];
        indexArr = new int[N + 1];
        list = new ArrayList<>();
        sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        list.add(Integer.MIN_VALUE);

        for (int i = 1; i <= N; i++) {
            int num = arr[i];
            if (num > list.get(list.size() - 1)) {
                list.add(num);
                indexArr[i] = list.size() - 1;
            } else {
                int left = 1;
                int right = list.size() - 1;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (list.get(mid) >= num) right = mid;
                    else left = mid + 1;
                }
                list.set(right, num);
                indexArr[i] = right;
            }
        }

        int lisLength = list.size() - 1;
        sb.append(lisLength).append("\n");

        Stack<Integer> stack = new Stack<>();
        int index = lisLength;

        for (int i = N; i > 0; i--) {
            if (indexArr[i] == index) {
                stack.push(arr[i]);
                index--;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb.toString().trim());
        sc.close();
    }
}
