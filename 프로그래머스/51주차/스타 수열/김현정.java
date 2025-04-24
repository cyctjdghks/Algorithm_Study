import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] a) {
        int answer = -1;
        
        //init
        HashMap<Integer, Integer> count = new HashMap<>();
        for(int i=0; i<a.length; i++){
            count.put(a[i], count.getOrDefault(a[i], 0) + 1);
        }
        ArrayList<Integer> sorted = (ArrayList<Integer>)
            count.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey).collect(Collectors.toList());
        
        for(int i=0; i<sorted.size(); i++){
            int max = count.get(sorted.get(i));
            if(answer >= max * 2)   break;
            int curr = getStarArrayLength(a, sorted.get(i));
            answer = Math.max(curr, answer);
        }
        return answer;
    }
    
    private int getStarArrayLength(int[] arr, int kyo){
        ArrayList<Integer> list = new ArrayList<>();
        int length = 0;
        for(int i=0; i<arr.length-1; i++){
            if(arr[i] != arr[i+1] && (arr[i] == kyo|| arr[i+1] == kyo)){
                length++;
                i++;
            }
        }
        return length*2;
    }
}
