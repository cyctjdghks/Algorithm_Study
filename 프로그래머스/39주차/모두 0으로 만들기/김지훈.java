import java.util.*;

class Solution {
    static int[] a;
    static int[][] edges;

    static long answer = 0;
    static long[] longArr;

    static List<Integer>[] A;
    static boolean[] visited;

    public long solution(int[] a, int[][] edges) {
        this.a = a;
        this.edges = edges;

        longArr = new long[a.length];
        A = new ArrayList[a.length];
        visited = new boolean[a.length];

        long sum = 0;   // a 배열의 전체 합
        for(int i = 0 ; i < a.length ; i++) {
            sum += a[i];
            A[i] = new ArrayList<>();
            longArr[i] = a[i];
        }

        // 전부 더해서 0이 안 되면 -1 리턴
        if(sum != 0)    return -1;

        // 인접 정보 저장
        for(int i = 0 ; i < edges.length ; i++) {
            A[edges[i][0]].add(edges[i][1]);
            A[edges[i][1]].add(edges[i][0]);
        }

        dfs(0);

        return answer;
    }

    private static long dfs(int v) {
        visited[v] = true;

        for(int i = 0 ; i < A[v].size() ; i++) {
            int next = A[v].get(i); // 인접 노드 가져오기

            if(!visited[next])
                longArr[v] += dfs(next);    // ⭐ dfs로 인접한 노드로부터 가중치 업데이트 (+1, -1 연산 효과. )
        }

        answer += Math.abs(longArr[v]); // ⭐ 현재 노드에서 이동한 값만큼 더하기

        return longArr[v];
    }
}
