class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = 4000000000000000L;
        long right = answer;
        long left = 0L;
        
        while(right >= left){
            long mid = (right + left) / 2L;
            if(isAvailableTime(mid, a, b, g, s, w, t)){
                right = mid - 1;
                answer = Math.min(mid, answer);
            }else{
                left = mid + 1;
            }
        }
        return answer;
    }
    
    private boolean isAvailableTime(long time, int wantedGold, int wantedSilver, int[] gold, int[] silver, int[] work, int[] halfTime){
        long totalGold = 0 , totalSilver = 0, total = 0;
        long[] count = new long[gold.length];
        for(int i=0; i<gold.length; i++){
            count[i] = time/(halfTime[i]*2L);
            if(time%(halfTime[i]*2L) >= halfTime[i])    count[i]++;
            
            totalGold += Math.min(work[i] * count[i], gold[i]);
            totalSilver += Math.min(work[i] * count[i], silver[i]);
            total = Math.min(count[i]*work[i], gold[i] + silver[i]);
        }
        
        return (wantedGold + wantedSilver <= total && 
                wantedGold <= totalGold && wantedSilver <= totalSilver);
    }
}
