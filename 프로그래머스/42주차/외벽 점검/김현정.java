import java.util.*;
import java.util.stream.Collectors;

class Solution {

    int[] weakArr;
    int answer = 10;
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;

        weakArr = weak;
        Arrays.sort(dist);
        //permutation
        boolean[] selected = new boolean[dist.length];
        select(selected, dist, 0);

        //rotate
        return answer;
    }

    //permutation
    private void select(boolean[] selected, int[] dist, int currIdx){
        if(currIdx == dist.length){
            answer = Math.min(getMinCount(), answer);
        }

        selected[currIdx] = true;
        select(selected, dist, currIdx + 1);
        selected[currIdx] = false;
        select(selected, dist, currIdx + 1);
    }

    private int getMinCount(boolean[] selected, int[] dist){
        int result = 10;
        List<Integer> friends = Arrays.stream(dist).filter(i -> selected[i]).boxed().collect(Collectors.toList());
        Collections.sort(friends, Collections.reverseOrder());

        for(int i=0; i<weakArr.length; i++){
            int currCount = 0;
            boolean[] isVisited = new boolean[weakArr.length];
            int[] currArr = new int[weakArr.length];
        }

        return result;
    }
}
