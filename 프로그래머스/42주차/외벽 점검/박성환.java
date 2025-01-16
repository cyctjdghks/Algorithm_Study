import java.util.*;

public class Solution {

    static int[] weak;
    static int[] dist;
    static int[][] weakCases;
    static int n;
    static int answer;

    public static int solution(int n, int[] weak, int[] dist) {
        weakCases = new int[weak.length][weak.length];
        answer = dist.length + 1;

        init(n, weak, dist);
        initWeakCases();
        dfs(new boolean[dist.length], new int[dist.length], 0);

        return answer == dist.length + 1 ? -1 : answer;
    }

    private static void init(int totalLength, int[] weakPoints, int[] distances) {
        n = totalLength;
        weak = weakPoints;
        dist = distances;
    }

    private static void initWeakCases() {
        int[] tempWeak = weak.clone();
        weakCases[0] = tempWeak.clone();

        for (int i = 1; i < weak.length; i++) {
            int temp = tempWeak[0];
            for (int j = 1; j < weak.length; j++) {
                tempWeak[j - 1] = tempWeak[j];
            }
            tempWeak[weak.length - 1] = temp + n;
            weakCases[i] = tempWeak.clone();
        }
    }

    private static void dfs(boolean[] visited, int[] currentPermutation, int idx) {
        if (idx == dist.length) {
            for (int[] weakCase : weakCases) {
                check(currentPermutation, weakCase);
            }
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                currentPermutation[idx] = dist[i];
                dfs(visited, currentPermutation, idx + 1);
                visited[i] = false;
            }
        }
    }

    private static void check(int[] distPermutation, int[] weakCase) {
        int cur = 0;
        int distIdx = 0;

        while (cur < weakCase.length && distIdx < distPermutation.length) {
            int next = cur + 1;
            while (next < weakCase.length && weakCase[cur] + distPermutation[distIdx] >= weakCase[next]) {
                next++;
            }
            cur = next;
            distIdx++;
        }

        if (cur == weakCase.length) {
            answer = Math.min(answer, distIdx);
        }
    }
}
