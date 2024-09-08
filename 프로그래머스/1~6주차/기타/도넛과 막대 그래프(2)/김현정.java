import java.util.*;
class Solution {
    ArrayList[] linkedlist;
    ArrayList<Integer> candidateList = new ArrayList<>();
    int[] answer;
    public int[] solution(int[][] edges) {
        int length = init(edges);
        
        for(Integer candidate : candidateList){
            ArrayList<Integer> linked = linkedlist[candidate];
            answer = {candidate, 0, 0, 0};
            int cnt = 0;
            for(Integer vertex : linked){
                cnt += dfs(linked);
            }
            
            if(cnt == length)   break;
        }
        return answer;
    }
    
    private int init(int[][] edges){
        
        int length = 0;
        for(int i=0; i<edges.length; i++){
            length = Math.max(length, Math.max(edges[i][0]. edges[i][1]));
        }
        
        linkedlist = new ArrayList<Integer>[length+1];
        boolean[] inboundCnt = new boolean[length+1];
        for(int i=1; i<length+1; i++){
            linkedlist[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edges.length; i++){
            linkedlist[edges[i][0]].add(edges[i][1]);
            inboundCnt[edges[i][1]] = true;
        }
        
        for(int i=1; i<length+1; i++){
            if(inboundCnt[i])   candidateList.add(i);
        }
        return length;
    }
    
    private int dfs(int vertex, int maxNumber){
        
    }
}
