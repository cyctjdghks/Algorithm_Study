import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        Arrays.sort(data, (o1, o2) -> {
            // data의 col번째 컬럼의 값이 동일한 경우, 첫 번째 컬럼의 값을 기준으로 내림차순
            // index로 변환해야하기 때문에 col-1
            if(o1[col-1] == o2[col-1]) return o2[0] - o1[0];
            // data의 col번째 컬럼의 값을 기준으로 오름차순
            else return o1[col-1] - o2[col-1];
        });
        
        // index로 변환해야하기 때문에 -1
        for(int i=row_begin-1;i<row_end;i++) {
            int sum = 0;
            
            for(int d : data[i]) {
                // 각 컬럼의 값을 i 로 나눈 나머지들의 합
                // index로 변환했기 때문에 +1
                sum += (d % (i+1));
            }
            
            // 모든 S_i를 누적하여 bitwise XOR 한 값
            answer ^= sum;
        }
        
        return answer;
    }
}