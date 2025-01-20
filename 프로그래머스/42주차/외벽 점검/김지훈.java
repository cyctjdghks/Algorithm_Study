import java.util.*;

public class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int l = weak.length;
        int[] weakLine = new int[2 * l];
        for (int i = 0; i < l; i++) {
            weakLine[i] = weak[i];
            weakLine[i + l] = weak[i] + n;
        }

        Arrays.sort(dist);
        reverse(dist);

        Set<Set<Integer>> repairedList = new HashSet<>();
        repairedList.add(new HashSet<>());
        int cnt = 0;

        for (int d : dist) {
            cnt++;
            List<Set<Integer>> repairs = new ArrayList<>();

            for (int i = 0; i < l; i++) {
                int start = weak[i];
                Set<Integer> repair = new HashSet<>();
                for (int j = i; j < i + l; j++) {
                    if (weakLine[j] <= start + d) {
                        repair.add(weakLine[j] % n);
                    } else {
                        break;
                    }
                }
                repairs.add(repair);
            }

            Set<Set<Integer>> can = new HashSet<>();
            for (Set<Integer> r : repairs) {
                for (Set<Integer> x : repairedList) {
                    Set<Integer> newSet = new HashSet<>(r);
                    newSet.addAll(x);
                    if (newSet.size() == l) {
                        return cnt;
                    }
                    can.add(newSet);
                }
            }
            repairedList = can;
        }

        return -1;
    }

    private void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        System.out.println(solution.solution(n, weak, dist)); // Expected output: 2
    }
}