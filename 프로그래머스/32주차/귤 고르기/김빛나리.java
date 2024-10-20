import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i=0;i<tangerine.length;i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
        }
        
        ArrayList<Integer> keySet = new ArrayList<>(map.keySet());
        
        // map의 value 값을 기준으로 내림차순
        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));

        for(Integer key : keySet) {
            k -= map.get(key);

            if(k <= 0) {
                answer++;
                break;
            }
            else answer++;
        }
        
        return answer;
    }
}