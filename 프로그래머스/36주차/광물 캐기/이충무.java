import java.util.*;

class Solution {
    private static final int[][] PICK_NUM = {
            { 1, 1, 1 },
            { 5, 1, 1 },
            { 25, 5, 1 }
    }; 

    public int solution(int[] picks, String[] minerals) {
        int pickCnt = Arrays.stream(picks).sum() * 5;
        if (pickCnt < minerals.length) { 
            minerals = Arrays.copyOfRange(minerals, 0, pickCnt);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i = 0; i < minerals.length; i += 5) {
            int num = 0;
            for (int j = i; j < i + 5 && j < minerals.length; j++) {
                switch (minerals[j]) {
                    case "diamond" -> num += PICK_NUM[2][0];
                    case "iron" -> num += PICK_NUM[2][1];
                    case "stone" -> num += PICK_NUM[2][2];
                }
            }
            pq.offer(new int[] { i, num });
        }

        int p = 0;
        for (int i = 0; i < picks.length; i++) {
            if (picks[i] > 0) {
                p = i;
                break;
            }
        }
        int answer = 0;
        while (!pq.isEmpty()) {
            int m = pq.poll()[0];
            for (int j = m; j < m + 5 && j < minerals.length; j++) { 
                switch (minerals[j]) {
                    case "diamond" -> answer += PICK_NUM[p][0];
                    case "iron" -> answer += PICK_NUM[p][1];
                    case "stone" -> answer += PICK_NUM[p][2];
                }
            }
            if (--picks[p] == 0) { 
                p++;
            }
        }

        return answer;
    }
}