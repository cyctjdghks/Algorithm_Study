class Solution {
    int[][] weak_cases;
    int[] Dist;
    int answer = 0;
    int weakLen = 0, distLen = 0;
    
    public int solution(int n, int[] weak, int[] dist) {
        weakLen = weak.length;
        distLen = dist.length;
        weak_cases = new int[weakLen][weakLen];
        Dist = dist;
        answer = distLen + 1;
        
        // 모든 weak 케이스 만들기
        // 모든 케이스를 만들 경우, 한방향으로만 탐색을 계속 진행해도 모든 경우를 다 탐색 가능
        weak_cases[0] = weak.clone();
        makeWeakCases(n, weak);
        
        // 모든 dist 케이스 만들기 -> 순열 이용
        makeDistCases(new boolean[distLen], new int[distLen], 0);
        
        // answer 값 그대로 = 친구들을 모두 투입해도 취약 지점을 전부 점검할 수 없는 경우
        if(answer == dist.length+1) return -1;
        
        return answer;
    }
    
    void makeWeakCases(int n, int[] weak) {
        int[] weak_case = weak.clone();
        
        // weak에서 나올 수 있는 모든 케이스 만들기
        // [1, 5, 6, 10] -> [5, 6, 10, 1+n] -> ...
        for(int i=1;i<weakLen;i++) {
            int temp = weak_case[0];
            
            for(int j=1;j<weakLen;j++) {
                weak_case[j-1] = weak_case[j];
            }
            weak_case[weak.length-1] = temp + n;
            
            // 만들어진 weak 케이스 저장
            weak_cases[i] = weak_case.clone();
        }
    }
    
    void makeDistCases(boolean[] visited, int[] dist_case, int idx) {
        // 모든 dist 탐색 완료한 경우
        if(idx == distLen) {
            // 만들어진 모든 weak 케이스에 대해 모든 dist 케이스 검사
            for(int[] weak_case : weak_cases) {
                check(dist_case, weak_case);
            }
        }
        
        // [3,5,7], [3,7,5], [5,3,7], [5,7,3], ...
        for(int i=0;i<distLen;i++) {
            // 방문한 적 없는 경우
            if(!visited[i]) {
                visited[i] = true;
                dist_case[idx] = Dist[i];
                
                makeDistCases(visited, dist_case, idx+1);
                
                dist_case[idx] = 0;
                visited[i] = false;
            }
        }
    }
    
    // 가정) weak = [1,3,4,9,10], dist = [3,5,7] 이용
    // 1번 지점에서 3짜리 dist가 사용 -> 1+3 = 4이므로, 3,4번 지점은 한번에 체크 가능
    // -> 다음 출발지는 9번 지점에서 5짜리 dist 사용 -> 9+5 = 14이므로, 10번 지점까지 한번에 체크 가능
    // -> 2개의 dist값만 사용해서 모두 탐색
    
    void check(int[] dist_case, int[] weak_case) {
        // weak index
        int cur = 0, next = 0;
        int dist_idx = 0;
        
        // 현재 weak와 dist index가 각각의 길이를 넘지않는 경우
        while(cur < weakLen && dist_idx < distLen) {
            // weak의 다음 index
            next = cur+1;
            
            // weak의 다음 index가 weak 길이를 넘지않고,
            // 현재 weak 지점과 dist 값을 더했을 때, 다음 weak 지점보다 큰 경우
            while(next < weakLen && weak_case[cur] + dist_case[dist_idx] >= weak_case[next]) {
                // 다음 지점 위치로 이동 -> 반복하면서 체크할 수 없는 지점위치까지 이동
                next++;
            }
            
            // 현재 위치 index 갱신
            cur = next;
            dist_idx++;
        }

        // weak 케이스는 탐색 완료고, dist 케이스는 최솟값 answer보다 작은 경우
        // answer의 초기값은 dist 길이 + 1
        if(cur == weakLen && dist_idx < answer)
            answer = dist_idx;
    }
}