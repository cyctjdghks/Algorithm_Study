class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int minNum = 1000000001;
        int minIdx= -1;
        for(int i=0;i<a.length;i++){
            if(minNum > a[i]){
                minNum = a[i];
                minIdx = i;
            }
        }
        
        int minLeftNum = a[0] + 1;
        int minRightNum = a[a.length-1] + 1;
        //left
        for(int i=0;i<minIdx;i++){
            if(a[i] < minLeftNum){
                answer++;
                minLeftNum=a[i];
            }
        }
        
        //right
        for(int i=a.length-1;i>minIdx;i--){
            if(a[i] < minRightNum){
                answer++;
                minRightNum=a[i];
            }
        }
        
        
        answer+=1;
        
        return answer;
    }
}