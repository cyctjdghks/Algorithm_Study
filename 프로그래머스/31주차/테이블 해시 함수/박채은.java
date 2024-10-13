import java.util.Arrays;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (o1,o2) -> {
            if(o1[col-1] == o2[col-1]) return o2[0] - o1[0];
            else return o1[col-1] - o2[col-1];
        });

        int result = 0;
        for(int i=row_begin-1;i<=row_end-1;i++){
            int S_i = 0;
            for(int d : data[i]){
                S_i += (d% (i+1));
            }
            result = result ^ S_i;
        }
        return result;
    }
}