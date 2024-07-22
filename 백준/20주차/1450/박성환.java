import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_1450 {

    static int N, C;
    static int[] arr;
    static ArrayList<Integer> sum1, sum2;
    static int res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        C = sc.nextInt();

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        // 배열을 반으로 나누기
        int[] firstHalf = Arrays.copyOfRange(arr, 0, N / 2);
        int[] secondHalf = Arrays.copyOfRange(arr, N / 2, N);

        // 각 부분집합의 합 계산
        sum1 = new ArrayList<>();
        sum2 = new ArrayList<>();
        getSubsetsSums(firstHalf, 0, 0, sum1);
        getSubsetsSums(secondHalf, 0, 0, sum2);

        // sum2 정렬
        Collections.sort(sum2);

        // 가능한 조합 계산
        res = countCombinations(C);

        System.out.println(res);
    }

    // DFS를 사용하여 부분집합의 합을 계산하는 함수
    private static void getSubsetsSums(int[] arr, int index, int currentSum, ArrayList<Integer> subsetsSums) {
        if (currentSum > C) return; // 가지치기 추가
        if (index == arr.length) {
            subsetsSums.add(currentSum);
            return;
        }
        // 현재 원소를 포함하지 않는 경우
        getSubsetsSums(arr, index + 1, currentSum, subsetsSums);
        // 현재 원소를 포함하는 경우
        getSubsetsSums(arr, index + 1, currentSum + arr[index], subsetsSums);
    }

    // 가능한 조합의 수를 계산하는 함수
    private static int countCombinations(int C) {
        int count = 0;

        for (int s1 : sum1) {
            if (s1 <= C) {
                count += upperBound(sum2, C - s1) + 1;
            }
        }

        return count;
    }

    // upperBound 함수 (이진 탐색)
    private static int upperBound(ArrayList<Integer> list, int key) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(mid) <= key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return high;
    }
}