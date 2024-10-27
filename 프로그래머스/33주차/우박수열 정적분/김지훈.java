import java.util.*;

class Solution {
    public static double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];

        List<Double> widthList = new ArrayList<>();

        while (k != 1) {
            int rem = k;
            if (k % 2 == 0) k /= 2;
            else k = 3 * k + 1;

            double width = (double) (rem + k) / 2;

            widthList.add(width);
        }

        double[] prefix = new double[widthList.size() + 1];

        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + widthList.get(i-1);
        }

        for (int i = 0; i < ranges.length; i++) {
            int start = ranges[i][0];
            int end = widthList.size() + ranges[i][1];

            if (end < start) {
                answer[i] = -1.0;
                continue;
            }

            answer[i] = prefix[end] - prefix[start];
        }
        return answer;
    }
}