import java.util.*;
class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();

        for(int i =1;i<=elements.length;i++){
            for(int cur = 0;cur<elements.length;cur++){
                int total = 0;
                for(int k= cur;k<cur+i;k++){
                    total += elements[k % elements.length];
                }
                set.add(total);
            }
        }
        return set.size();
    }
}
