import java.util.*;

public class Solution {

    public int solution(int[] diffs, int[] times, long limit) {
        int maxValue = Arrays.stream(diffs).max().getAsInt();
        int minValue = 1;
        int level = (maxValue + minValue) / 2;

        while (minValue <= maxValue) { 
            long time = calculateTime(diffs, times, level);

            if (limit == time) {
                return level;
            } else if (limit > time) {
                long nextTime = (level == 1) ? limit + 1 : calculateTime(diffs, times, level - 1);

                if (limit < nextTime) {
                    return level;
                } else {
                    maxValue = level - 1;
                }
            } else {
                minValue = level + 1;
            }

            level = (maxValue + minValue) / 2;
        }

        return -1;
    }

    private long calculateTime(int[] diffs, int[] times, int level) {
        long totalTime = 0;

        for (int i = 0; i < diffs.length; i++) {
            int levelDiff = diffs[i] - level;

            if (levelDiff <= 0) {
                totalTime += times[i];
            } else {
                int previousTime = (i == 0) ? 0 : times[i - 1];
                int currentTime = times[i];
                int additionalTime = (previousTime + currentTime) * levelDiff + currentTime;

                totalTime += additionalTime;
            }
        }

        return totalTime;
    }
}