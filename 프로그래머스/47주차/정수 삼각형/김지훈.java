class Solution {
    public int solution(int[][] triangle) {
        int height = triangle.length;

        // DP 배열을 삼각형 자체로 사용 (원본 데이터 수정)
        for (int i = height - 2; i >= 0; i--) { // 아래에서 위로 이동
            for (int j = 0; j < triangle[i].length; j++) {
                // 현재 위치에서 아래층 두 개 중 큰 값과 현재 값 더하기
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }

        // 꼭대기의 최대값이 정답
        return triangle[0][0];
    }
}
