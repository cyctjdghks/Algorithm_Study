import java.util.*;

class Solution {
    public int solution(int[] cards) {
        boolean[] opened = new boolean[cards.length];
        
        ArrayList<ArrayList<Integer>> totalList = new ArrayList<>();
        ArrayList<Integer> g = new ArrayList<>();
        g.add(cards[0]);
        
        int curNo = cards[0];
        opened[0] = true;
        
        while(!allOpened(opened)){
            if(opened[curNo-1] == false){
                g.add(cards[curNo-1]);
                opened[curNo-1] = true;
                curNo = cards[curNo-1];
            }
            else{ // 새로운 그룹 생성
                totalList.add(g);
                g = new ArrayList<>();
                
                for(int i=0;i<opened.length;i++){
                    if(opened[i]==false){
                        curNo = i+1;
                    }
                }
            }
        }
        totalList.add(g);
        
        if (totalList.size() < 2) {
            return 0;
        }
        
        for (int i = 0; i < totalList.size(); i++) {
            System.out.println("List " + i + ": " + totalList.get(i));
        }
        
        totalList.sort((a, b) -> Integer.compare(b.size(), a.size()));
        
        return totalList.get(0).size() * totalList.get(1).size();
    }
    
    public boolean allOpened(boolean[] box){
        for(int i=0;i<box.length;i++){
            if(box[i]==false){
                return false;
            }
        }
        return true;
    }
}