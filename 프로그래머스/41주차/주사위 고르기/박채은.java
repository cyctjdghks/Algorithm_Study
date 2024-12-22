import java.util.*;
class Solution {
    static int N;
    static List<Integer> arrA;
    static List<Integer> arrB;
    static int[][] dices;
    static int[] choiceOfA;
    static List<Integer> temp = new ArrayList<>();
    static int max = Integer.MIN_VALUE;

    // 주사위를 굴려서 A, B의 조합을 구한다.
    public static void roll(){
        arrA = new ArrayList<>();
        arrB = new ArrayList<>();

        int[][] diceA = new int[N / 2][6];
        int[][] diceB = new int[N / 2][6];
        int a = 0, b=0;

        for(int i=0;i<N;i++){
            if(temp.contains(i)){ // A가 고른 주사위인 경우
                diceA[a] = dices[i];
                a+=1;
            }else{
                diceB[b] = dices[i];
                b+=1;
            }
        }

        makeCombOfRoll(0,0,diceA, arrA);
        makeCombOfRoll(0,0,diceB, arrB);
    }

    // 주사위를 굴린 경우의 수 구하기
    public static void makeCombOfRoll(int dept, int sum, int[][] dice, List<Integer> arrTemp){
        if(dept == N/2){
            arrTemp.add(sum);
            return;
        }
        for(int i=0;i<6;i++){
            int Sum = sum + dice[dept][i];
            makeCombOfRoll(dept+1, Sum, dice, arrTemp);
        }
    }

    // 이분탐색으로 승리할 확률 구하기
    public static int calculatePercent(){
        roll();

        Collections.sort(arrB);

        int cnt = 0;
        for(int i=0;i<arrA.size();i++){
            int val = arrA.get(i);
            int left = 0;
            int right = arrB.size()-1;

            int index = Integer.MIN_VALUE;
            while(left <= right){
                int mid = (left+right) / 2;
                if(arrB.get(mid) < val){
                    left = mid +1;
                    index = Math.max(index, mid);
                }else{
                    right = mid -1;
                }
            }
            if(index != Integer.MIN_VALUE){
                cnt += index+1;
            }
        }
        return cnt;
    }

    // 주사위의 조합
    public static void combination(int dept, int start){
        if(dept == N/2){ // N/2개를 모두 고른 경우
            int per = calculatePercent();
            if(max < per){
                max = per;
                for(int i=0;i< temp.size();i++){
                    choiceOfA[i] = temp.get(i) +1;
                }
            }
            return;
        }

        for(int i=start;i<N;i++){
            temp.add(i);
            combination(dept+1, i+1);
            temp.remove(temp.size()-1);
        }
    }

    public static int[] solution(int[][] dice) {
        N = dice.length;
        dices = dice;
        choiceOfA = new int[N/2];

        // arrA, arrB를 구한다.
        combination(0,0);

        return choiceOfA;
    }
}