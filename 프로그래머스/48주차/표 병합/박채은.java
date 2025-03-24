import java.util.*;

class Solution {
    public int[] parentTable = new int[2501];
    public String[] cellValues = new String[2501];

    // UNION-FIND
    public int findRoot(int cellId) {
        if (parentTable[cellId] == cellId)
            return cellId;
        else
            return parentTable[cellId] = findRoot(parentTable[cellId]);
    }

    public void unionCells(int cellA, int cellB) {
        cellA = findRoot(cellA);
        cellB = findRoot(cellB);
        if (cellA != cellB)
            parentTable[cellB] = cellA;
    }

    public int getCellId(int row, int col) {
        return 50 * (row - 1) + col;
    }

    public String[] solution(String[] commands) {
        for (int i = 1; i <= 2500; i++) {
            parentTable[i] = i;
            cellValues[i] = "";
        }

        List<String> output = new ArrayList<>();
        for (String commandLine : commands) {
            StringTokenizer tokenizer = new StringTokenizer(commandLine);
            String command = tokenizer.nextToken();

            if ("UPDATE".equals(command)) {
                if (tokenizer.countTokens() == 2) {
                    String targetValue = tokenizer.nextToken();
                    String newValue = tokenizer.nextToken();
                    for (int i = 1; i <= 2500; i++) {
                        if (targetValue.equals(cellValues[i]))
                            cellValues[i] = newValue;
                    }
                }
                else {
                    int row = Integer.parseInt(tokenizer.nextToken());
                    int col = Integer.parseInt(tokenizer.nextToken());
                    String newValue = tokenizer.nextToken();
                    int cellId = getCellId(row, col);
                    cellValues[findRoot(cellId)] = newValue;
                }
            } else if ("MERGE".equals(command)) {
                int row1 = Integer.parseInt(tokenizer.nextToken());
                int col1 = Integer.parseInt(tokenizer.nextToken());
                int row2 = Integer.parseInt(tokenizer.nextToken());
                int col2 = Integer.parseInt(tokenizer.nextToken());
                int cellId1 = getCellId(row1, col1);
                int cellId2 = getCellId(row2, col2);
                int root1 = findRoot(cellId1);
                int root2 = findRoot(cellId2);

                if (root1 == root2) continue;

                String mergedValue = cellValues[root1].isBlank() ? cellValues[root2] : cellValues[root1];
                cellValues[root1] = "";
                cellValues[root2] = "";
                unionCells(root1, root2);
                cellValues[root1] = mergedValue;
            } else if ("UNMERGE".equals(command)) {
                int row = Integer.parseInt(tokenizer.nextToken());
                int col = Integer.parseInt(tokenizer.nextToken());
                int cellId = getCellId(row, col);
                int rootId = findRoot(cellId);
                String originalValue = cellValues[rootId];

                cellValues[rootId] = "";
                cellValues[cellId] = originalValue;

                List<Integer> cellsToReset = new ArrayList<>();
                for (int i = 1; i <= 2500; i++) {
                    if (findRoot(i) == rootId)
                        cellsToReset.add(i);
                }

                for (Integer resetCell : cellsToReset)
                    parentTable[resetCell] = resetCell;
            } else if ("PRINT".equals(command)) {
                int row = Integer.parseInt(tokenizer.nextToken());
                int col = Integer.parseInt(tokenizer.nextToken());
                int cellId = getCellId(row, col);
                int rootId = findRoot(cellId);

                if (cellValues[rootId].isBlank())
                    output.add("EMPTY");
                else
                    output.add(cellValues[rootId]);
            }
        }
        return output.toArray(new String[0]);
    }
}