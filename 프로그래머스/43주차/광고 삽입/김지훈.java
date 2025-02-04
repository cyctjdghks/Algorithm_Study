import java.util.*;

public class Solution {

    public static String solution(String play, String adv, String[] logs) {
        int playTime = toSeconds(play);
        int advTime = toSeconds(adv);

        // 로그 시작 및 종료 시간 정리
        List<int[]> timeLogs = new ArrayList<>();
        for (String log : logs) {
            int start = toSeconds(log.substring(0, 8));
            int end = toSeconds(log.substring(9, 17));
            timeLogs.add(new int[]{start, 1}); // 시작점
            timeLogs.add(new int[]{end, 0});  // 종료점
        }
        timeLogs.sort(Comparator.comparingInt(a -> a[0]));

        // 구간별 시청자 수 계산
        int[] viewers = new int[playTime];
        int currentViewers = 0, prevTime = 0;
        for (int[] log : timeLogs) {
            int time = log[0];
            if (currentViewers > 0) {
                for (int t = prevTime; t < time; t++) {
                    viewers[t] += currentViewers;
                }
            }
            currentViewers += log[1] == 1 ? 1 : -1;
            prevTime = time;
        }

        // 광고 시작 지점 계산
        long maxViewers = 0, currentSum = 0;
        int bestStartTime = 0;

        // 초기 광고 시간의 시청자 수 합계 계산
        for (int i = 0; i < advTime; i++) {
            currentSum += viewers[i];
        }
        maxViewers = currentSum;

        // 슬라이딩 윈도우로 최대 시청자 수 합계 찾기
        for (int i = advTime; i < playTime; i++) {
            currentSum += viewers[i] - viewers[i - advTime];
            if (currentSum > maxViewers) {
                maxViewers = currentSum;
                bestStartTime = i - advTime + 1;
            }
        }

        return formatTime(bestStartTime);
    }

    private static int toSeconds(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }

    private static String formatTime(int seconds) {
        int hours = seconds / 3600;
        seconds %= 3600;
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static void main(String[] args) {
        String play = "02:03:55";
        String adv = "00:14:15";
        String[] logs = {
                "01:20:15-01:45:14",
                "00:25:50-00:48:29",
                "00:40:31-01:00:00",
                "01:37:44-02:02:30",
                "01:30:59-01:53:29"
        };
        System.out.println(solution(play, adv, logs)); // 결과: "01:30:59"
    }
}
