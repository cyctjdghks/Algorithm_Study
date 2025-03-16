class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;

        int height = triangle.length;
        int[][] sumTree = new int[height][height];

        int sum;
        sumTree[0][0] = triangle[0][0];
        for (int row = 1; row < height; row++) {
            for (int col = 0; col <= row; col++) {
                sum = 0;
                if (col == 0) {
                    sum += sumTree[row - 1][col];
                } else if (col == row) {
                    sum += sumTree[row - 1][col - 1];
                } else {
                    sum += (sumTree[row - 1][col - 1] > sumTree[row - 1][col]) ? sumTree[row - 1][col - 1] : sumTree[row - 1][col];
                }
                sumTree[row][col] = sum + triangle[row][col];
            }
        }

        for (int i = 0; i < height; i++) {
            answer = (answer < sumTree[height - 1][i]) ? sumTree[height - 1][i] : answer;
        }

        return answer;
    }
}
