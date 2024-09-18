class Solution {
    int[] gDiffs;
    int[] gTimes;
    long gLimit;
    
    public int solution(int[] diffs, int[] times, long limit) {
        int start = 0, end = Integer.MAX_VALUE;
        gDiffs = diffs;
        gTimes = times;
        gLimit = limit;
        
        while(start < end) {
            int mid = (start + end) / 2;
            long result = calculate(mid);
        
            if(result > limit) start = mid + 1;
            else end = mid;
        }
        
        return start;
    }
    
    public long calculate(int level) {
        if(level < 1) return Long.MAX_VALUE;
        
        long time = 0;
        
        for(int i=0;i<gDiffs.length;i++) {
            // diff ≤ level인 경우
            if(gDiffs[i] <= level) time += gTimes[i];
            // diff > level인 경우
            else{
                // 처음인 경우, 이전 퍼즐이 없으므로 현재 퍼즐의 소요 시간만
                int useTimes = i == 0 ? gTimes[i] : gTimes[i] + gTimes[i-1];
                int cnt = gDiffs[i] - level;
                
                time += useTimes * cnt + gTimes[i];
            }
        }
        
        return time;
    }
}