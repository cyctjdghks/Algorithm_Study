import java.util.*;

class Solution {

    static int sign = 0; // 최대 회원 수
    static int earn = 0; // 최대 수익

    public int[] solution(int[][] users, int[] emoticons) {
        // 완전탐색
        
        // n 명의 사용자들에게
        // m 개를 할인
        
        // 할인율은 10, 20, 30, 40 4가지
        // 이모티콘은 최대 7가지
        // 유저는 최대 100명
        
        // 자기 기준에 따라 일정 비율 이상 할인 => 모두 구매
        // 구매 비용이 일정 가격 이상이 된다면 => 서비스 가입
        // answer[0] 값이 최대 중 answer[1] 이 최대
        // answer[0] 은 플러스 가입자 수
        // answer[1] 은 가입하지 않은 사람들의 구매 비용

        int[] answer = new int[2];

        int[] arr = new int[emoticons.length];

        comb(arr, 0, users, emoticons);

        answer[0] = sign;
        answer[1] = earn;

        return answer;
    }

    public void comb(int[] arr, int start, int[][] users, int[] emoticons) {

        if (start == arr.length) {
            calculate(arr, users, emoticons);
            return;
        }

        for (int i = 10; i <= 40; i += 10) {
            arr[start] = i;
            comb(arr, start + 1, users, emoticons);
        }

    }

    public void calculate(int[] arr, int[][] users, int[] emoticons) {

        int count = 0;
        int earn_t = 0;

        for (int[] user : users) {
            int discount = user[0];
            int price = user[1];
            int sum = 0;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= discount) {
                    sum += (emoticons[i] / 100) * (100 - arr[i]);
                }
            }

            if (sum >= price) {
                count++;
            } else {
                earn_t += sum;
            }
        }


        if (count > sign) {
            sign = count;
            earn = earn_t;
            return;
        } else if (count == sign) {
            if (earn < earn_t) {
                earn = earn_t;
            }
        }
    }

}
