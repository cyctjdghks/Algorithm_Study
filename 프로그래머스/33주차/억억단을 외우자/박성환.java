import java.util.*;

class Solution {    
    public int[] solution(int e, int[] starts) {
        // 적당한 수 e
        // 임의의 수 s
        // starts[i] ~ e 까지
        // 등장한 수가 여러개라면 가장 작은 수
        
        // 약수 구하기 
        int [] memo = new int [e + 1];
        Arrays.fill(memo, 1);
        for(int i = 2; i <= e; i++){
            for(int j = 1; j * i <= e; j++){
                memo[i *j]++;
            }
        }
        int size = starts.length; // 100000 최대 
        ArrayList<int[]> list = new ArrayList<>();
        for(int i = 0; i < size; i++){
            list.add(new int [] {i, starts[i]});
        }
        // starts 숫자가 큰 순서대로 정렬
        list.sort((o1,o2)->{
           return o2[1] - o1[1];
        });
        
        int end = e; // 탐색 범위 
        int max = 1;
        int v   = e;
        int [] answer = new int [size];
        for(int i = 0; i < size; i++){
            int [] temp = list.get(i);
            int start = temp[1];
            for(int j = end; j >= start; j--){
                if(memo[j] >= max){ // 크거나 같으면 갱신 
                    max = memo[j];
                    v = j;
                }    
            }
            end = start; // 범위 줄이기 
            answer[temp[0]] = v; // 정답 기록 
        }
        return answer;
    }
}