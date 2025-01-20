class Solution {
    static int N, answer;
    static int[] weak, dist;
    static int weakSize, distSize;
    static int[] fullWeak;
    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        answer = Integer.MAX_VALUE;
        weakSize = weak.length;
        distSize = dist.length;

        this.weak = weak;
        this.dist = dist;

        // weak 원형 리스트 만들기
        makeWeakArray();

        // dist 순열 만들기
        for(int i=1; i<dist.length+1; i++) {
            permutation(new boolean[i], new int[i], 0, i);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    void makeWeakArray(){
        fullWeak = new int[2*weakSize -1];

        for(int i=0;i<weakSize;i++){
            fullWeak[i] = weak[i];
        }
        for(int i=weakSize;i< fullWeak.length;i++){
            fullWeak[i] = weak[i - weakSize] + N;
        }
    }

    void permutation(boolean[] visited, int[] result, int idx, int r){
        if(idx == r){
            check(result);
        }

        for(int i=0;i<r;i++){
            if(visited[i]){
                continue;
            }
            visited[i] = true;
            result[idx] = dist[i];
            permutation(visited, result, idx+1, r);
            visited[i] = false;
        }

    }

    void check(int[] result){
        for(int i=0;i<weakSize;i++){ // 시작점
            int idx = 0;
            int cur = 0;
            int next = 0;
            for(cur=i;cur<i+weakSize;){ // 끝나는 지점
                next = cur +1;
                while (next < i+weakSize && fullWeak[cur] + result[idx] >= fullWeak[next]){
                    next +=1;
                }
                cur = next;
                idx+=1;
                if(idx == result.length){
                    break;
                }
            }
            if((cur-i) == weakSize && idx < answer){
                answer = idx;
            }
        }
    }
}