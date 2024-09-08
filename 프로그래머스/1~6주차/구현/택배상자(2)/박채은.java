// 순서대로 택배를 가져올 수 있는데, 배송할 택배가 아니면 보조 컨테이너밸트에 둠
// 보조 컨테이너밸트: 스택, top에 있는 것만 꺼낼 수 있다.
import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int[] mainVelt = {1, order.length};
        Stack<Integer> subVelt = new Stack<>();

        for(int i: order){
            // 1. 메인 밸트에 존재하는 경우
            if (i>= mainVelt[0]){
                // mainVelt[0] ~ (i-1)를 보조 벨트로 이동
                for(int j=mainVelt[0];j<i; j++){
                    subVelt.push(j);
                }
                // i를 트럭에 싣기
                mainVelt[0] = i+1;
                answer+=1;
            }
            else{
                if(subVelt.peek() == i){
                    subVelt.pop();
                    answer+=1;
                }
                else{
                    break;
                }
            }
        }
        return answer;
    }
}