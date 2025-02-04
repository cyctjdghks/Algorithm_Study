import java.util.*;

class Solution {

    public String solution(String playTime, String advTime, String[] logs) {
        int play = parseTime(playTime);
        int adv = parseTime(advTime);
        int[] viewers = new int[360_000];

        for (String log : logs) {
            String[] times = log.split("-");
            int start = parseTime(times[0]);
            int end = parseTime(times[1]);
            for (int i = start; i < end; i++) {
                viewers[i]++;
            }
        }

        long currentSum = 0;
        for (int i = 0; i < adv; i++) {
            currentSum += viewers[i];
        }

        long maxSum = currentSum;
        int startTime = 0;

        for (int i = adv; i < play; i++) {
            currentSum += viewers[i] - viewers[i - adv];
            if (currentSum > maxSum) {
                maxSum = currentSum;
                startTime = i - adv + 1;
            }
        }

        return formatTime(startTime);
    }

    private int parseTime(String time) {
        String[] hms = time.split(":");
        return Integer.parseInt(hms[0]) * 3600 + Integer.parseInt(hms[1]) * 60 + Integer.parseInt(hms[2]);
    }

    private String formatTime(int seconds) {
        int h = seconds / 3600;
        int m = (seconds % 3600) / 60;
        int s = seconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
