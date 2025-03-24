import java.util.*;

class Solution {
    static final int SIZE = 51; // 1-based index
    int[] parent = new int[SIZE * SIZE]; // Union-Find parent
    String[] table = new String[SIZE * SIZE]; // 각 셀의 값

    public String[] solution(String[] commands) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < SIZE * SIZE; i++) {
            parent[i] = i; // 초기 부모는 자기 자신
        }

        for (String cmd : commands) {
            String[] parts = cmd.split(" ");
            String action = parts[0];

            if (action.equals("UPDATE")) {
                if (parts.length == 4) {
                    int r = Integer.parseInt(parts[1]);
                    int c = Integer.parseInt(parts[2]);
                    String value = parts[3];
                    int id = rcToId(r, c);
                    int root = find(id);
                    table[root] = value;
                } else {
                    String value1 = parts[1];
                    String value2 = parts[2];
                    for (int i = 0; i < SIZE * SIZE; i++) {
                        if (table[i] != null && table[i].equals(value1)) {
                            table[i] = value2;
                        }
                    }
                }
            } else if (action.equals("MERGE")) {
                int r1 = Integer.parseInt(parts[1]);
                int c1 = Integer.parseInt(parts[2]);
                int r2 = Integer.parseInt(parts[3]);
                int c2 = Integer.parseInt(parts[4]);

                int id1 = rcToId(r1, c1);
                int id2 = rcToId(r2, c2);

                int root1 = find(id1);
                int root2 = find(id2);

                if (root1 == root2) continue;

                // 병합: 한 쪽으로 몰아주기
                String value = (table[root1] != null) ? table[root1] : table[root2];
                union(root1, root2);
                int newRoot = find(root1);
                table[newRoot] = value;
            } else if (action.equals("UNMERGE")) {
                int r = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                int id = rcToId(r, c);
                int root = find(id);
                String cellValue = table[root];

                List<Integer> groupMembers = new ArrayList<>();
                for (int i = 0; i < SIZE * SIZE; i++) {
                    if (find(i) == root) {
                        groupMembers.add(i);
                    }
                }
                for (int member : groupMembers) {
                    parent[member] = member;
                    table[member] = null;
                }
                table[id] = cellValue; // 원래 셀은 값 유지
            } else if (action.equals("PRINT")) {
                int r = Integer.parseInt(parts[1]);
                int c = Integer.parseInt(parts[2]);
                int id = rcToId(r, c);
                int root = find(id);
                String value = table[root];
                result.add(value == null ? "EMPTY" : value);
            }
        }

        return result.toArray(new String[0]);
    }

    // 좌표를 ID로 변환
    private int rcToId(int r, int c) {
        return (r - 1) * SIZE + (c - 1);
    }

    // Union-Find: find
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union-Find: union
    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}
