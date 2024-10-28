import java.util.ArrayList;

class Solution {
    ArrayList<Integer> yValue = new ArrayList<>();

    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];

        // 우박수열
        yValue.add(k);
        Collatz(k);

        for(int i=0;i<ranges.length;i++){
            int a= ranges[i][0];
            int b= ranges[i][1];
            if(a > yValue.size()-1 + b){
                answer[i] = -1;
                continue;
            }
            answer[i] = calculate(a, yValue.size()-1 + b);
        }

        return answer;
    }

    public void Collatz(int k){
        while(k > 1){
            if(k%2 == 0){
                k /= 2;
            }
            else{
                k = (k*3) + 1;
            }
            yValue.add(k);
        }
    }

    public double calculate(int start, int end){
        double result = 0;
        for(int i = start;i<end;i++){
            int h1 = yValue.get(i);
            int h2 = yValue.get(i+1);

            result += ((h1 + h2) / 2.0);
        }
        return result;
    }
}