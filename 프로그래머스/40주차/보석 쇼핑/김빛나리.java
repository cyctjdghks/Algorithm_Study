import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        // 보석의 종류
        HashSet<String> set = new HashSet<>();
        // HashSet<String> set = new HashSet<>(Arrays.asList(gems));
        
        // 보석의 종류별 개수
        HashMap<String, Integer> map = new HashMap<>();
        // 보석 순서대로 저장
        Queue<String> queue = new LinkedList<>();
        
        for(String gem : gems) {
            set.add(gem);
        }
        
        // 종류가 1개인 경우
        if(set.size() == 1) return new int[]{1, 1};
        
        // 진열대 시작, 체크하면서 바뀌는 임시 시작 위치
        int start = 0, temp_start = 0;
        // 구간
        int distance = Integer.MAX_VALUE;
        
        for(String gem: gems) {
            queue.add(gem);
            map.put(gem, map.getOrDefault(gem, 0) + 1);

            while(true) {
                String g = queue.peek();
                
                // 해당 보석의 갯수가 1개 이상인 경우
                if(map.get(g) > 1) {
                    map.put(g, map.get(g) - 1);
                    queue.poll();
                    // 임시 시작 위치 이동
                    temp_start++;
                }
                else break;
            }

            // 모든 종류의 보석을 구매 가능하고, 보석이 담은 queue의 길이가 distance보다 작을 경우
            if(map.size() == set.size() && distance > queue.size()) {
                distance = queue.size();
                start = temp_start;
            }
        }
        
        // start는 0부터 시작했기 때문에 +1, 시작위치부터 구해준 거리까지 
        return new int[]{start + 1, start + distance};
    }
}