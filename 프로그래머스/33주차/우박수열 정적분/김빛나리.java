import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        // k는 무조건 count하니까
        int cnt = 1;
        ArrayList<Double> list = new ArrayList<>();
        
        while(k != 1) {
            list.add((double) k);
            cnt++;
            
            if(k % 2 == 0) k /= 2;
            else k = (k * 3) + 1;
        }
        
        // 1은 빠져나오면서 list에 안들어가기 때문에
        list.add((double) k);
        
        // 정적분(넓이)를 구하기 위해서는 각 범위의 사다리꼴 넓이를 구하면 된다.
        // 사다리꼴 넓이 : ((윗변+아랫변) * 높이) / 2
        
        // 찍히고 점들을 기준으로 사다리꼴 넓이
        double[] area = new double[cnt];
        
        for(int i=1;i<cnt;i++) {
            // 높이는 무조건 1이라 생략
            area[i] = (list.get(i-1) + list.get(i)) / 2;
        }
    
        
        // 넓이 누적합
        double[] sum = new double[cnt];
        sum[1] = area[1];
        
        for(int i=2;i<cnt;i++){
            sum[i] = (area[i] + sum[i-1]);
        }

        for(int i=0;i<ranges.length;i++){
            int start = ranges[i][0];
            int end = (cnt-1) + ranges[i][1];

            // start부터 end까지 정적분(넓이)
            if(end > start) {
                // 소수점 1자리까지만
                double val = sum[end] - sum[start];
                String str = String.format("%.1f", val);
                answer[i] = (Double.parseDouble(str));
            }
            // 조건: 주어진 구간의 시작점이 끝점보다 커서 유효하지 않은 구간일 경우, -1로 정의
            else if(start > end) answer[i] = -1.0;
            // 구간이 같을 경우
            else answer[i] = 0.0;
        }

        return answer;
    }
}