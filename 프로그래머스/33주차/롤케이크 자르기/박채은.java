import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        // map에 전체 topping add
        for(int t : topping){
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        for(int t: topping){
            set.add(t);
            map.put(t, map.getOrDefault(t,0)-1);
            if(map.get(t) == 0){
                map.remove(t);
            }

            // 사이즈가 같은 경우
            if(map.size() == set.size()){
                answer += 1;
            }
        }

        return answer;
    }
}