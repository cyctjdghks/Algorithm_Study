class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int play = convertSecond(play_time);
        int adv = convertSecond(adv_time);
        int[] time = new int[play+1];
        
        for(String log : logs) {
            String[] l = log.split("-");
            int s = convertSecond(l[0]);
            int e = convertSecond(l[1]);

            for(int i=s;i<e;i++) {
                time[i]++;
            }
        }
        
        long cur = 0;
        // 0초 ~ adv초까지 시청하고 있는 수
        for(int i=0;i<adv;i++) {
            cur += time[i];
        }
        
        long max = cur;
        int start = 0;
        // 0초 ~ adv초까지 이미 count 했으니까 adv초 ~ play초까지 탐색
        for(int i=adv;i<play;i++) {
            // 누적 count
            cur += time[i] - time[i-adv];
            
            // 현재 누적 count 수가 max보다 클 경우
            if(cur > max) {
                max = cur;
                // 공익광고 재생 시작 시간
                start = i - adv + 1;
            }
        } 
        
        return String.format("%02d:%02d:%02d", start / 3600, (start / 60) % 60, start % 60);
    }
    
    int convertSecond(String time) {
        String[] t = time.split(":");
        
        return Integer.parseInt(t[0]) * 3600
            + Integer.parseInt(t[1]) * 60
            + Integer.parseInt(t[2]);
    }
}