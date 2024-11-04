import java.util.*;

class Solution {
    public int solution(int[] elements) {
        // 중복되는 값 제거
        HashSet<Integer> set = new HashSet<>();
        
        // 부분 수열은 길이가 1부터 elements의 전체 길이 만큼까지 나올 수 있다.
        for(int i=1;i<=elements.length;i++) {
            // elements 배열 index
			for(int j=0;j<elements.length;j++) {
				int sum = 0;
                
                // 인덱스부터 길이까지 더하기
				for (int k=j;k<i+j;k++) {
                    // 원형 수열이기 때문에 마지막 인덱스에서 초과한 길이만큼 요소를 다시 0번째부터
					sum += elements[k % elements.length];
				}
                
				set.add(sum);
			}
		}
        
        return set.size();
    }
}