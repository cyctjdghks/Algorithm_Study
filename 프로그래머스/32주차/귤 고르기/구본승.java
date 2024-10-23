import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : tangerine) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        ArrayList<Integer> valueList = new ArrayList<>(map.values());
        Collections.sort(valueList, Collections.reverseOrder());
        for (int i : valueList) {
            if (sum + i >= k) {
                answer++;
                break;
            } else {
                sum += i;
                answer++;
            }
        }
        
        return answer;
    }
}