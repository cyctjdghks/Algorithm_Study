import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        int len = lighthouse.length;
        // 한 뱃길의 양쪽 끝 등대 번호
        HashSet<Integer> edge = new HashSet<>(); 
        // 한 뱃길의 양쪽 끝 등대와 연결된, 반드시 켜야 하는 등대 번호
        HashSet<Integer> light = new HashSet<>(); 
            
        for(int i=0;i<n;i++) {
            // 각 등대에 연결된 등대 수
            int[] linkedCnt = new int[n + 1];
            // 반드시 켜야 하는 등대를 킨 후 남은 등대 쌍
            int[][] remaining = new int[len][2]; 
            // remaining에 담긴 등대 쌍의 수
            int remainingCnt = 0;
                
            for(int j=0;j<len;j++) {
                linkedCnt[lighthouse[j][0]]++;
                linkedCnt[lighthouse[j][1]]++;
            }
                
            for(int j=0;j<linkedCnt.length;j++) {
                // 연결된 등대가 하나뿐인 경우, 한 뱃길의 양쪽 끝 등대로 판단
                if (linkedCnt[j] == 1) edge.add(j);
            }
                
            for(int j=0;j<len;j++) {
                // 연결된 등대 중 하나라도 양쪽 끝 등대인 경우
                if(edge.contains(lighthouse[j][0]) || edge.contains(lighthouse[j][1])) {
                    // 양쪽 끝 등대의 연결된 등대를 켜주기
                    if(edge.contains(lighthouse[j][0])) light.add(lighthouse[j][1]);
                    else light.add(lighthouse[j][0]);
                }
            }
                
            for(int j=0;j<len;j++) {
                // 켜진 등대의 영향으로 밝힐 수 없는 등대 쌍인 경우
                if (!light.contains(lighthouse[j][0]) && !light.contains(lighthouse[j][1])) {
                    remaining[remainingCnt] = lighthouse[j];
                    remainingCnt++;
                }
            }
                
            // 현재 켜진 등대로 모든 등대를 밝힐 수 있는 경우
            if(remainingCnt == 0) break;

            // 현재 켜진 등대로 밝힐 수 없는 등대 1쌍만 존재하는 경우
            if(remainingCnt == 1) { 
                // 남은 1쌍의 등대 중 하나의 등대만 키면 되기 때문에 1만 증가
                answer += 1; 
                break;
            }
                
            // 현재 켜진 등대로 밝힐 수 없는 등대 쌍의 수가 lighthouse 길이 미만인 경우
            if(remainingCnt < len) { 
                // 현재 켜진 등대로 밝힐 수 없는 등대 쌍만 고려하기 위해
                // lighthouse를 remaining으로 초기화
                lighthouse = new int[remainingCnt][2];
                    
                for(int j=0;j<remainingCnt;j++) {
                    lighthouse[j] = remaining[j];
                }
                
                len = remainingCnt;
            }
        }
            
        // 켜진 등대의 수 더하기
        answer += light.size();
        
        return answer;
    }
}