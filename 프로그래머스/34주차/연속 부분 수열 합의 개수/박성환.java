import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        
        HashSet<Integer> set = new HashSet<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i<2; i++) {
            for (int element : elements) {
                list.add(element);
            }
        }
        for (int i = 0; i < elements.length; i++) {
            for (int j = 1; j <= elements.length; j++) {
                List<Integer> subList = list.subList(i, i+j);
                int sum = 0;
                for (int num : subList)
                    sum += num;
                set.add(sum);
            }
        }
        answer = set.size();
        return answer;
    }
}