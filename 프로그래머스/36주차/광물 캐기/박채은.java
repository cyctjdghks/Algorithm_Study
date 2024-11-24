import java.util.*;

// 최소한의 피로도
// 광산에 있는 모든 광물을 캐거나, 더 사용할 곡괭이가 없을 때까지 광물을 캡니다.

// dia, iron, stone 순서
class Solution {
    static List<int[]> list;
    static int[][] hard = {{1,1,1}, {5,1,1}, {25,5,1}};

    public int solution(int[] picks, String[] minerals) {
        list = new ArrayList<>();
        int totalTool = Arrays.stream(picks).sum();

        for(int i=0;i<minerals.length;i+=5){
            if(totalTool == 0) break;

            // 5개씩 그룹 묶음
            int dia = 0, iron = 0, stone = 0;
            for(int j=i;j<i+5;j++){
                if(j == minerals.length) break;

                // 피로도 계산
                int minType = 0;
                if(minerals[j].equals("iron")) minType=1;
                else if (minerals[j].equals("stone")) minType = 2;

                dia += hard[0][minType];
                iron += hard[1][minType];
                stone += hard[2][minType];
            }
            list.add(new int[]{dia, iron, stone});
            totalTool-=1;
        }

        // 돌곡괭이를 기준으로 오름차순 정렬
        Collections.sort(list, (o1,o2) -> (o2[2]-o1[2]));

        // 최소 피로도 구하기
        // dia > iron > stone 순위로 먼저 사용
        int result = 0;
        for(int[] tool : list){
            if(picks[0] > 0){
                result += tool[0];
                picks[0]-=1;
                continue;
            }
            if(picks[1] > 0){
                result += tool[1];
                picks[1]-=1;
                continue;
            }
            if(picks[2] > 0){
                result += tool[2];
                picks[2]-=1;
                continue;
            }
        }
        return result;
    }
}