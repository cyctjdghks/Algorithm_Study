import java.util.*;
class Solution {
    Map<String, Integer> map = new HashMap();
    Set<String> set = new HashSet();
    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        // 총 갯수
        int k = gems.length;
        int type = 1;
        int start = 0;
        int temp = 0;

        for(int i=0;i<k;i++){
            set.add(gems[i]);
        }
        type = set.size();

        Queue<String> que = new LinkedList<>();
        for(int i=0;i<gems.length;i++){
            map.put(gems[i], map.getOrDefault(gems[i], 0) +1);
            que.add(gems[i]);

            while(true){
                String gem = que.peek();
                if(map.get(gem) > 1){
                    map.put(gem, map.get(gem) -1);
                    que.poll();
                    temp +=1;
                }else{
                    break;
                }
            }

            if(map.size() == type){
                if(k > que.size()){
                    k = que.size();
                    start = temp;
                }
            }
        }

        return new int[] {start + 1, start + k};
    }
}