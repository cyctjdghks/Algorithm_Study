import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        // 수확한 귤 중 k 개만 상자에 하나씩
        // 종류가 최소가 되도록
        
        int answer = 0;
        
                int sum = 0; // 고를 귤의 개수
        int cnt = 0; // 최솟값 카운트
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int num : tangerine) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        ArrayList<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list, Collections.reverseOrder());
        
        for (int i : list) {
            if (sum + i >= k) {
                cnt++;
                break;
            } else {
                sum += i;
                cnt++;
            }
        }
        
        answer = cnt;
        
        return answer;
    }
}