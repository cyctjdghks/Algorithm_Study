package coplit.dailyCoding.study.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 수열의 크기 N
        int[] num = new int[N]; // 수열을 저장하는 배열
        int[] dp = new int[N]; // 각 숫자가 가장 긴 증가하는 부분 수열에 포함되었을 때의 길이를 저장하는 배열
        List<Integer> L = new ArrayList<>(); // 가장 긴 증가하는 부분 수열(LIS)를 저장하는 리스트
        Stack<Integer> s = new Stack<>(); // 역추적을 위한 스택
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int idx = 0, dptmp = 0; // idx는 LIS의 마지막 원소의 위치를, dptmp는 LIS의 길이를 저장
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            num[i] = a; // 입력받은 숫자를 num 배열에 저장
            if (L.isEmpty()) {
                L.add(num[i]); // LIS가 비어있다면, 현재 숫자를 추가
                dp[i] = 1; // 현재 숫자가 LIS에 포함되었을 때의 길이는 1
            } else {
                if (L.get(L.size() - 1) < num[i]) {
                    L.add(num[i]); // LIS의 마지막 숫자보다 현재 숫자가 크다면, 현재 숫자를 추가
                    dp[i] = L.size(); // 현재 숫자가 LIS에 포함되었을 때의 길이를 저장
                } else {
                    int pos = Collections.binarySearch(L, num[i]); // 이분 탐색을 통해 현재 숫자가 들어갈 위치를 찾음
                    if (pos < 0) pos = -(pos + 1); // 이분 탐색 결과가 음수라면, -(pos + 1)을 통해 적절한 위치로 변환
                    L.set(pos, num[i]); // LIS를 갱신
                    dp[i] = pos + 1; // 현재 숫자가 LIS에 포함되었을 때의 길이를 저장
                }
            }
            if (dp[i] > dptmp) {
                idx = i; // LIS의 마지막 원소의 위치를 갱신
                dptmp = dp[i]; // LIS의 길이를 갱신
            }
        }
        System.out.println(L.size()); // LIS의 길이 출력
        s.push(num[idx]); // LIS의 마지막 원소를 스택에 추가
        for (int i = idx - 1; i >= 0; i--) {
            if (num[i] < num[idx] && dp[i] + 1 == dp[idx]) {
                idx = i; // LIS의 이전 원소의 위치로 갱신
                s.push(num[i]); // LIS의 이전 원소를 스택에 추가
            }
        }
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " "); // 스택이 비어있지 않은 동안, 스택의 상단 원소를 출력하고 제거. 이는 LIS를 역순으로 출력하는 과정입니다.
        }
    }
}
