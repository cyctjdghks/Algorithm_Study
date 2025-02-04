class Solution {
    static int[] times = new int[360_000];
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        // 1. 초로 시간 변환
        int playTime = convertToSec(play_time);
        int advTime = convertToSec(adv_time);

        // 2. times에 재생횟수 기록
        for(String log : logs){
            String[] l = log.split("-");
            int start = convertToSec(l[0]);
            int end = convertToSec(l[1]);
            for(int i=start;i<end;i++){
                times[i]++;
            }
        }

        // 3. 슬라이딩 윈도우
        long max = 0;
        long sum = 0;
        int max_start = 0;
        for(int i=0;i<advTime;i++){
            sum+= times[i];
        }

        max = sum;
        for(int i=advTime;i<playTime;i++){
            sum += (times[i] - times[i-advTime]);
            if(sum > max){
                max = sum;
                max_start = i-advTime +1;
            }
        }

        // 4. String으로 변환
        answer = convertToString(max_start);

        return answer;
    }

    public int convertToSec(String time){
        String[] t = time.split(":");
        int h = Integer.parseInt(t[0]);
        int m = Integer.parseInt(t[1]);
        int s = Integer.parseInt(t[2]);

        return (h*3600) + (m*60) + s;
    }

    public String convertToString(int time){
        int h = (time/3600);
        int m = (time%3600) / 60;
        int s = ((time%3600) % 60);

        String t = "";
        if(h < 10){
            t+= "0" + h + ":";
        }
        else t += (h+":");
        if(m < 10){
            t+= "0" + m + ":";
        }
        else t += (m+":");
        if(s < 10){
            t+= "0" + s;
        }
        else t += s;
        return t;
    }

}