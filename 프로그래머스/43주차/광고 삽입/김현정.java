import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String startResult = "";

        PriorityQueue<Integer> start = new PriorityQueue<>();
        PriorityQueue<Integer> end = new PriorityQueue<>();

        init(start, end, logs);

        int maxPlayTime = 0;
        if(play_time.equals(adv_time))   return "00:00:00";
        String[] logStart = Arrays.stream(logs).map(i -> i.split("-")[0]).distinct().toArray(String[]::new);
        for(int i=0; i<logStart.length; i++){
            int currTime = calPlayTime(logStart[i], adv_time, start, end, logs);

            if(currTime > maxPlayTime){
                maxPlayTime = currTime;
                startResult = logStart[i];
            }
        }
        return startResult;
    }

    private void init(PriorityQueue<Integer> start, PriorityQueue<Integer> end, String[] logs){

        //start asc
        Arrays.sort(logs, Comparator.comparing(o -> o.split("-")[0]));

        for(int i=0; i<logs.length; i++){
            String[] time = logs[i].split("-");

            start.add(convertToInt(time[0]));
            end.add(convertToInt(time[1]));
        }
    }

    private int convertToInt(String time){

        String[] times = time.split(":");

        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        int second = Integer.parseInt(times[2]);

        return second + minute*60 + hour*60*60;
    }

    private int calPlayTime(String advStart, String advTime, PriorityQueue<Integer> start, PriorityQueue<Integer> end, String[] logs){
        int startTime = convertToInt(advStart);
        int endTime = startTime + convertToInt(advTime);

        int curr = startTime;
        while(start)

    }
}
