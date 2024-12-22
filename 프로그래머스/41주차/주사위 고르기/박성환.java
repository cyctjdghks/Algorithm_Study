import java.util.*;

class Solution {
    private int N;
    private int[][] D;
    private int[] maxList;
    private int maxWin;
    private int tmpSum;

    private int[] A;
    private int[] B;

    private List<Integer> sumList;

    public int[] solution(int[][] dice) {
        // A 가 먼저 n/2 개 가져감
        // B 가 나머지
        // 점수 같다면 무승부
        
        N = dice.length;
        D = dice;
        maxList = new int[N / 2];
        maxWin = Integer.MIN_VALUE;
        A = new int[N / 2];
        B = new int[N / 2];
        
        dfs(0, 0);
        
        return maxList;
    }

    // 조합을 선택하는 DFS 메서드
    public void dfs(int currentIndex, int selectedCount) {
        if (selectedCount == N / 2) {
            // 선택이 끝났으면 B 그룹을 구성
            int index = 0;
            for (int i = 0; i < N; i++) {
                boolean isInA = false;
                for (int j = 0; j < N / 2; j++) {
                    if (A[j] == i) {
                        isInA = true;
                        break;
                    }
                }
                if (!isInA) {
                    B[index++] = i;
                }
            }
            calCnt();
            return;
        }

        if (currentIndex >= N) return;

        A[selectedCount] = currentIndex;
        dfs(currentIndex + 1, selectedCount + 1);
        dfs(currentIndex + 1, selectedCount);
    }

    // 점수를 계산하고 최대 점수를 업데이트
    public void calCnt() {
        sumList = new ArrayList<>();
        tmpSum = 0;

        // B의 모든 경우의 수 계산
        calSumB(0, 0);
        Collections.sort(sumList);

        // A의 모든 경우의 수 계산 후 이분 탐색
        calSumA(0, 0);

        if (maxWin < tmpSum) {
            maxWin = tmpSum;
            for (int i = 0; i < N / 2; i++) {
                maxList[i] = A[i] + 1;
            }
        }
    }

    // A의 모든 경우의 수를 계산하고 B의 합과 비교 (이분 탐색)
    public void calSumA(int currentIndex, int sum) {
        if (currentIndex == N / 2) {
            // sum을 가지고 이분 탐색 수행
            int lo = 0;
            int hi = sumList.size();
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (sumList.get(mid) < sum) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            tmpSum += lo;
            return;
        }

        for (int i = 0; i < 6; i++) {
            calSumA(currentIndex + 1, sum + D[A[currentIndex]][i]);
        }
    }

    // B의 모든 경우의 수를 계산
    public void calSumB(int currentIndex, int sum) {
        if (currentIndex == N / 2) {
            sumList.add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            calSumB(currentIndex + 1, sum + D[B[currentIndex]][i]);
        }
    }
}
