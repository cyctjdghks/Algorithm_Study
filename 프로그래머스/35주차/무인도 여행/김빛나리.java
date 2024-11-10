import java.util.*;

class Solution {
    int[][] map;
    
    public int[] solution(String[] maps) {
        map = new int[maps.length][maps[0].length()];
        ArrayList<Integer> list = new ArrayList<>();
        
        // int 배열로 변환
        for(int i=0;i<maps.length;i++) {
            int idx = 0;
            String[] mapSplit = maps[i].split("");
            
            for(String m : mapSplit) {
                map[i][idx++] = m.equals("X") ? 0 : Integer.parseInt(m);
            }
        }
        
        // dfs
        for(int i=0;i<map.length;i++) {
            for(int j=0;j<map[0].length;j++) {
                // 연결된 섬의 total 식량
                int total = dfs(i, j);
                
                if(total > 0) list.add(total);
            }
        }
        
        if(list.size() == 0) return new int[]{-1};
        else {
            Collections.sort(list);
            int[] answer = new int[list.size()];

            for(int i=0;i<list.size();i++) {
                answer[i] = list.get(i);
            }

            return answer;
        }
    }
    
    int dfs(int i, int j) {
        // 지도를 벗어난 경우
        if(i < 0 || i == map.length || j < 0 || j == map[0].length) return 0;
        // 바다 or 이미 식량을 계산한 경우
        else if(map[i][j] == 0) return 0;
        
        int food = map[i][j];
        // 해당 위치 식량 계산 완료 처리
        map[i][j] = 0;
        
        // 해당 위치 식량 + 상하좌우 탐색한 값
        return food + dfs(i+1, j) + dfs(i-1, j) + dfs(i, j-1) + dfs(i, j+1);
    }
}