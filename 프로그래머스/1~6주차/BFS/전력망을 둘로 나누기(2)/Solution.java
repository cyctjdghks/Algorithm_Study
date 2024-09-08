// 두 송전탑의 갯수 차이의 최솟값을 return
// 전선 중 하나만 끊음 -> 전체를 돌면서 하나만 끊고나서 최솟값 찾기
import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int[][] matrix = new int[n+1][n+1];
        int min = Integer.MAX_VALUE;
        int result=0;

        // 네트워크 형성
        for(int i=0;i<wires.length;i++){
            int start = wires[i][0];
            int end = wires[i][1];

            matrix[start][end] = 1;
            matrix[end][start] = 1;
        }

        // 하나씩 전선을 끊어봄
        for(int i=0;i<wires.length;i++){
            int start = wires[i][0];
            int end = wires[i][1];

            matrix[start][end] = 0;
            matrix[end][start] = 0;

            // bfs
            result = bfs(n, matrix);
            if(result < min){
                min = result;
            }

            // 끊은 전선을 다시 원복
            matrix[start][end] = 1;
            matrix[end][start] = 1;
        }

        return min;
    }

    public int bfs(int n, int[][] matrix){
        int visited[] = new int[n+1];
        LinkedList<Integer> que = new LinkedList<>();
        int cnt = 1; // 시작점 포함

        // 시작점은 1로 통일
        que.add(1);
        visited[1] = 1;

        while(!que.isEmpty()){
            int node = que.poll();
            for(int i=1;i<=n;i++){
                if(visited[i] == 0 && matrix[node][i] == 1){
                    que.add(i);
                    visited[i] = 1;
                    cnt+=1;
                }
            }
        }
        return (int)Math.abs((n-cnt)-cnt);
    }
}