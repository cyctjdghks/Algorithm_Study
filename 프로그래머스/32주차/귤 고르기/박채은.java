import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int t: tangerine){
            map.put(t, map.getOrDefault(t,0) + 1);
        }

        ArrayList<Integer> value = new ArrayList<>(map.values());
        Collections.sort(value, Collections.reverseOrder());

        for(int v : value){
            k-= v;
            answer+=1;
            if(k <= 0){
                break;
            }
        }

        return answer;
    }
}