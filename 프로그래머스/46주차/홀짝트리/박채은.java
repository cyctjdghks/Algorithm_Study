import java.util.*;

class Solution {
    private static final int MAX = 400400;
    private static int[] parent = new int[MAX];
    private static int[] degree = new int[MAX];
    private static int[] rootGroup = new int[MAX];
    private static int[] nonRootGroup = new int[MAX];

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void merge(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public int[] solution(int[] nodes, int[][] edges) {
        int n = nodes.length;
        Map<Integer, Integer> node2idx = new HashMap<>();

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            node2idx.put(nodes[i], i);
        }

        for (int[] edge : edges) {
            int u = node2idx.get(edge[0]);
            int v = node2idx.get(edge[1]);
            degree[u]++;
            degree[v]++;
        }

        for (int[] edge : edges) {
            int u = node2idx.get(edge[0]);
            int v = node2idx.get(edge[1]);
            merge(u, v);
        }

        for (int i = 0; i < n; i++) {
            int representative = find(i);
            if ((nodes[i] % 2) == (degree[i] % 2)) {
                rootGroup[representative]++;
            } else {
                nonRootGroup[representative]++;
            }
        }

        int hTreeCount = 0, rTreeCount = 0;

        for (int i = 0; i < n; i++) {
            if (find(i) != i) {
                continue;
            }
            if (rootGroup[i] == 1) {
                hTreeCount++;
            }
            if (nonRootGroup[i] == 1) {
                rTreeCount++;
            }
        }

        return new int[]{hTreeCount, rTreeCount};

    }
}