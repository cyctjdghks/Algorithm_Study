class Solution {
    public int solution(int[] a) {
        if(a.length == 1) return 0;
        
        int answer = 0;
        // a 원소의 등장횟수
        int[] count = new int[a.length];
        
        for(int i : a) {
            count[i]++;
        }
        
        // 공통된 원소로 선택할 값
        for(int i=0;i<a.length;i++) {
            // 빈 수열은 허용되지 않음
            if(count[i] == 0) continue;
            // 가장 많이 사용된 횟수보다 작은 경우, 긴 스타수열 만들 수 없음
            if(count[i] <= answer) continue;
            
            // 해당 숫자가 사용된 횟수
            int cnt = 0;
            
            // 배열 탐색 반복
            for(int j=0;j<a.length-1;j++) {
                // 인접한 2개의 값 중 적어도 하나는 공통된 원소를 가지고 있어야 함
                if(a[j] != i && a[j+1] != i) continue;
                // 인접한 2개의 값이 동일한 경우
                if(a[j] == a[j+1]) continue;
                
                cnt++;
                j++;
            }
            
            answer = Math.max(answer, cnt);
        }
        
        // 공통된 원소가 사용될 때마다 인접한 값 하나씩을 더 가지고 있으므로
        return answer*2;
    }
}