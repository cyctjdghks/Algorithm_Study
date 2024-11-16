import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int num = picks[0] + picks[1] + picks[2];
        // 5개 연속으로 캐니까 5로 나눠주고, 딱 나누어 떨어질 수도 있으니 +1
        // 다이아몬드(0), 철(1), 돌(2)로 캤을때의 피로도를 저장하기 위해서 size = 3
        int[][] section = new int[minerals.length / 5 + 1][3];
        
        for(int i=0;i<minerals.length && num>0;i++) {
            switch(minerals[i].charAt(0)) {
                case 'd':
                    section[i / 5][0] += 1;
                    section[i / 5][1] += 5;
                    section[i / 5][2] += 25;
                    break;
                case 'i':
                    section[i / 5][0] += 1;
                    section[i / 5][1] += 1;
                    section[i / 5][2] += 5;
                    break;
                case 's':
                    section[i / 5][0] += 1;
                    section[i / 5][1] += 1;
                    section[i / 5][2] += 1;
            }
            
            // 곡괭이 사용
            if(i % 5 == 4) num--;            
        }
        
        // "돌로 캤을때 가장 많은 비용이 드는" 섹션을 다이아몬드 곡괭이 사용
        // 돌 곡괭이의 피로도 기준으로 내림차순 정렬
        Arrays.sort(section, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2] < o2[2]) return 1;
                else return -1;
            }
        });
        
        int pick = 0;
        for(int i=0;i<section.length;i++) {
            // 3종류의 곡괭이를 다 사용하지 않고, 해당 곡괭이를 더 사용하지 못할 경우
            while(pick < 3 && picks[pick] == 0) pick++;
            
            // 이미 3종류의 곡괭이를 다 사용한 경우
            if(pick == 3) break;
            
            // 해당 곡괭이 사용 완료
            picks[pick]--;
            // 해당 곡괭이의 피로도 더하기
            answer += section[i][pick];
        }
        
        return answer;
    }
}