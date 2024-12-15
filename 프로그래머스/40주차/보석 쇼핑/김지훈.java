import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        Set<String> allGems = new HashSet<>(Arrays.asList(gems));

        Map<String, Integer> gemsMap = new HashMap<>();


        int gemsSize = allGems.size();
        int minLength = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int startIndex = 0;

        while(end < gems.length){

            gemsMap.put(gems[end], gemsMap.getOrDefault(gems[end], 0) + 1);
            end++;

            while(gemsMap.size() == gemsSize){
                if(end - start < minLength){
                    minLength = end - start;
                    startIndex = start;
                }

                gemsMap.put(gems[start], gemsMap.get(gems[start]) -1);
                if(gemsMap.get(gems[start]) == 0 ){
                    gemsMap.remove(gems[start]);
                }
                start++;
            }


        }

        return new int[]{startIndex + 1, startIndex + minLength};
    }
}