import java.util.*;

class Solution {
    static ArrayList<int[]> combination = new ArrayList<>();
    static int userCnt = 0;
    static int emoticonCnt = 0;
    static int plusUserCnt = 0;
    static int emoPay = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        userCnt = users.length;
        emoticonCnt = emoticons.length;
        comb(0, new int[emoticonCnt]);

        for(int[] com : combination){
            calculate(users, emoticons, com);
        }

        int[] answer = new int[] { plusUserCnt, emoPay };

        return answer;
    }

    public void calculate(int[][] users, int[] emoticons, int[] comb){
        int totalCnt = 0; // 이모티콘 플러스 사용자
        int totalPay = 0; // 이모티콘 판매액
        for(int i=0;i<users.length;i++){
            int dis = users[i][0];
            int price = users[i][1];
            int sum = 0;

            for(int j=0;j<emoticons.length;j++){
                if(dis <= comb[j]){ // 구매
                    sum += ((emoticons[j] * (100 - comb[j])) / 100.0);
                }
            }

            if(sum >= price){
                totalCnt += 1;
            }
            else{
                totalPay+= sum;
            }
        }

        // 이모티콘 plus 가입수가 더 많은 경우
        if(plusUserCnt < totalCnt){
            plusUserCnt = totalCnt;
            emoPay = totalPay;
        } else if (plusUserCnt ==totalCnt){
            if(emoPay < totalPay){
                emoPay = totalPay;
            }
        }
    }

    // 재귀
    public static void comb(int dept, int[] result){
        if(dept == emoticonCnt){
            combination.add(result.clone());
            return;
        }

        for(int p = 10;p<=40;p+=10){
            result[dept] = p;
            comb(dept+1, result);
        }
    }
}


