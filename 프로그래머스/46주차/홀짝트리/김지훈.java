import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int n = nodes.length;
        // 1. 각 노드 번호를 0 ~ n-1 인덱스로 매핑
        HashMap<Integer, Integer> nodeIndex = new HashMap<>(n * 2);
        for (int i = 0; i < n; i++) {
            nodeIndex.put(nodes[i], i);
        }

        // 2. 인접 리스트 그래프와 degree 배열 생성
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            int ia = nodeIndex.get(a);
            int ib = nodeIndex.get(b);
            graph[ia].add(ib);
            graph[ib].add(ia);
            degree[ia]++;
            degree[ib]++;
        }

        // 3. 각 연결 요소(트리)를 BFS로 찾으며 해당 트리가 홀짝/역홀짝 트리가 될 수 있는지 검사
        boolean[] visited = new boolean[n];
        int holjjakCount = 0;
        int reverseCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ArrayList<Integer> comp = new ArrayList<>();
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i);
                visited[i] = true;
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    comp.add(cur);
                    for (int nxt : graph[cur]) {
                        if (!visited[nxt]) {
                            visited[nxt] = true;
                            queue.offer(nxt);
                        }
                    }
                }

                int compSize = comp.size();
                // 각 트리에서 각 노드에 대해 홀짝/역홀짝 조건을 검사
                // 노드의 번호 parity: f = 1이면 홀수, 0이면 짝수.
                // degree의 홀짝: dParity = degree mod 2.
                // 홀짝 트리 조건:
                //   - 루트: dParity == f
                //   - 비루트: dParity == (1 - f)
                // 역홀짝 트리 조건은 반대
                int countRoot_h = 0, countNonRoot_h = 0;
                int countRoot_rev = 0, countNonRoot_rev = 0;

                for (int idx : comp) {
                    int nodeValue = nodes[idx];
                    int d = degree[idx];
                    int dParity = d & 1; // degree mod2
                    int f = (nodeValue & 1); // 1 if odd, 0 if even

                    // 홀짝 트리 조건
                    if (dParity == f) countRoot_h++;        // 루트 조건: dParity == f
                    if (dParity == (1 - f)) countNonRoot_h++; // 비루트 조건: dParity == (1 - f)

                    // 역홀짝 트리 조건
                    if (dParity == (1 - f)) countRoot_rev++;   // 루트 조건: dParity == (1 - f)
                    if (dParity == f) countNonRoot_rev++;        // 비루트 조건: dParity == f
                }

                // 한 트리에서 단 하나의 노드만 루트가 되어야 하고, 나머지는 비루트여야 함.
                if (countRoot_h == 1 && countNonRoot_h == compSize - 1)
                    holjjakCount++;
                if (countRoot_rev == 1 && countNonRoot_rev == compSize - 1)
                    reverseCount++;
            }
        }
        return new int[]{holjjakCount, reverseCount};
    }
}
