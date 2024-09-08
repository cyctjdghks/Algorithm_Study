// 마지막 테스트 케이스 (35)만 통과 안 됨

import java.util.*;

class Solution {
    public List<Integer>[] graph;
    public int incomingEdgeNum[];
    public int outcomingEdgeNum[];
    public int size;
    public boolean[] visited;

    public int E;

    public int[] solution(int[][] edges) {
        // edge의 갯수 구하기
        for(int [] edge: edges){
            int e = Math.max(edge[0],edge[1]);
            if(size < e){
                size = e;
            }
        }

        // 그래프 초기화
        graph = new ArrayList[size+1];
        for (int i = 1; i <= size; i++) {
            graph[i] = new ArrayList<>();
        }

        incomingEdgeNum = new int[size+1];
        outcomingEdgeNum = new int[size+1];
        visited = new boolean[size+1];

        for(int [] edge: edges){
            int s = edge[0];
            int e = edge[1];
            graph[s].add(e);
            incomingEdgeNum[e] +=1;
            outcomingEdgeNum[s] +=1;
        }

        // 상관없는 정점임을 확인
        // 상관없는 정점은 본인에게 들어오는 간선이 없어야 함
        // 나가는 정점은 2개 이상 3개 이하
        for (int i = 1; i <= size; i++) {
            if(incomingEdgeNum[i] == 0 && graph[i].size() >= 2){
                E = i;
                visited[E] = true;
                break;
            }
        }

        int totalGraph = graph[E].size();
        int[] answer = new int[4];
        answer[0] = E;

        // 상관없는 정점과의 연결 끊기
        removeLink();

        // 막대 그래프의 일부인 경우
        // 들어오는 간선, 나가는 간선이 하나
        // 가장 마지막 정점은 나가는 간선 없음, 들어오는 간선 하나
        answer[2] = findBarGraph();

        // 8자 모양 그래프
        // 생성한 정점과 연결되어 있는 것 빼고는,
        // 중간 정점은 나가고 들어오는 간선 2개씩
        answer[3] = findEightGraph();
        answer[1] = totalGraph - answer[2] - answer[3];


        return answer;
    }

    public void removeLink(){
        outcomingEdgeNum[E] = 0;
        // income도 하나씩 줄이기
        for (int i = 0; i < graph[E].size(); i++) {
            int e = graph[E].get(i);
            incomingEdgeNum[e] -= 1;
        }
        graph[E] = new ArrayList<>();
    }

    public int findEightGraph(){
        int cnt=0;
        for (int i = 1; i <= size; i++) {
            if(i == E) continue;
            if(outcomingEdgeNum[i] == 2 && incomingEdgeNum[i] == 2){
                cnt+=1;
                visited[i] = true;
            }
        }
        return cnt;
    }

    public int findBarGraph(){
        int cnt=0;
        for (int i = 1; i <= size; i++) {
            if(i == E) continue;
            if(outcomingEdgeNum[i] == 0){
                cnt+=1;
                visited[i] = true;
            }
        }
        return cnt;
    }
}