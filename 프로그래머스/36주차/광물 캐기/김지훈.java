import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        int dia = picks[0];
        int iron = picks[1];
        int stone = picks[2];
        int totalPicks = dia + iron + stone;

        int[][] picksFatigue = new int[totalPicks][6];

        for (int i = 0; i < Math.min(totalPicks * 5, minerals.length); i += 5) {
            int sum = 0;
            int fatigue = 0;


            for (int j = i; j < Math.min(i + 5, minerals.length); j++) {

                if (minerals[j].equals("diamond")) {
                    fatigue = 25;
                } else if (minerals[j].equals("iron")) {
                    fatigue = 5;
                } else {
                    fatigue = 1;
                }

                sum += fatigue;
                picksFatigue[i / 5][j % 5 + 1] = fatigue;
            }

            picksFatigue[i / 5][0] = sum;
        }

        Arrays.sort(picksFatigue, (a, b) -> b[0] - a[0]);

        for (int i = 0; i < picksFatigue.length; i++) {
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

            for (int j = 1; j < 6; j++) {
                switch (pick) {
                    case "diamond":
                        answer += (int) Math.ceil(picksFatigue[i][j] / 25.0);
                        break;
                    case "iron":
                        answer += (int) Math.ceil(picksFatigue[i][j] / 5.0);
                        break;
                    case "stone":
                        answer += picksFatigue[i][j];
                        break;
                }
            }
        }


        return answer;
    }
}