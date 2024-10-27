import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        // 철수 토핑
        HashSet<Integer> a = new HashSet<>();
        // 동생 토핑
        HashMap<Integer, Integer> b = new HashMap<>();
        
        // 동생 토핑 map에 전부 저장
        for(int t : topping) {
            b.put(t, b.getOrDefault(t, 0) + 1);
        }
        
        for(int t : topping) {
            // 철수가 동생 토핑 하나 가져가기
            a.add(t);
            b.put(t, b.get(t)-1);
            
            // 동생 토핑 중에서 갯수가 0일 경우, 삭제
            if(b.get(t) == 0) b.remove(t);

            // 토핑이 공평하게 나눠졌을 경우
            if (a.size() == b.size()) answer++;
        }
        
        return answer;
    }
}