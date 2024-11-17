import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        // 곡갱이들 picks
        // [dia, iron, stone]
        // 광물들 minerals
        // diamond : 다이아몬드, iron : 철, stone : 돌
        // 아무 곡갱이를 골라서 사용할 수 없을 때까지 사용
        // 광물은 순서대로
        // 모든 광물을 캐거나 곡갱이들을 다 쓸떄까지
        // 그리디
        
         int answer = 0;

        int dia = picks[0];
        int iron = picks[1];
        int stone = picks[2];

        // 곡갱이 총 개수
        int totalPick = dia + iron + stone;

        // 광석 총 개수
        int n = minerals.length;

        int[][] mineralGroup = new int[totalPick][6];

        // 5개 기준으로 피로도 계산
        for (int i = 0; i < Math.min(totalPick * 5, n); i += 5) {
            int sum = 0;
            int damage = 0;

            for (int j = i; j < Math.min(i + 5, n); j++) {
                switch (minerals[j]) {
                    case "diamond":
                        damage = 25;
                        break;
                    case "iron":
                        damage = 5;
                        break;
                    case "stone":
                        damage = 1;
                        break;
                }

                sum += damage;
                mineralGroup[i / 5][j % 5 + 1] = damage;
            }

            mineralGroup[i / 5][0] = sum;
        }

        // 피로도 합 기준으로 내림차순 정렬
        Arrays.sort(mineralGroup, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });

        for (int i = 0; i < totalPick; i++) {
            int[] temp = mineralGroup[i];
            String pick = "";

            if (dia > 0) {
                dia -= 1;
                pick = "diamond";
            } else if (iron > 0) {
                iron -= 1;
                pick = "iron";
            } else if (stone > 0) {
                stone -= 1;
                pick = "stone";
            }

			// 광물별 피로도 계산 
            for (int j = 1; j < 6; j++) {
                switch (pick) {
                    case "diamond":
                        answer += (int) Math.ceil(temp[j] / 25.0);
                        break;
                    case "iron":
                        answer += (int) Math.ceil(temp[j] / 5.0);
                        break;
                    case "stone":
                        answer += temp[j];
                        break;
                }
            }
        }

        return answer;
    }
}