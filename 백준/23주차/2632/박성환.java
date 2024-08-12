import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_2632 {

    static int target;
    static int m;
    static int n;
    static int[] A;
    static int[] B;
    static boolean[] check;
    static ArrayList<Integer> AList = new ArrayList<>();
    static ArrayList<Integer> BList = new ArrayList<>();
    static int ans=0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        target = sc.nextInt();
        m = sc.nextInt();
        n = sc.nextInt();

        A=new int[m];
        B=new int[n];

        for (int i = 0; i <m ; i++) {
            A[i] = sc.nextInt();
        }

        for (int i = 0; i <n ; i++) {
            B[i] = sc.nextInt();
        }

        //A 피자에 대한 부분합 만들기
        for (int i = 0; i <m ; i++) {
            check = new boolean[m];
            check[i]=true;
            getSum(A[i], i, i+1, check, A, AList);
        }
        //B 피자에 대한 부분합 만들기
        for (int i = 0; i <n ; i++) {
            check = new boolean[n];
            check[i]=true;
            getSum(B[i], i, i+1, check, B, BList);
        }

        AList.add(0);
        BList.add(0);

        Collections.sort(AList);
        Collections.sort(BList);

        int leftIdx= 0;
        int rightIdx = BList.size() -1;

        while(leftIdx < AList.size() && rightIdx >=0){//두 배열 사이즈 안에서 실행
            // left, right index의 value 가져옴.
            int lv = AList.get(leftIdx);
            int rv = BList.get(rightIdx);

            if(lv+rv ==target){// 목표값이 등장했으므로
                int lc =0;
                int rc = 0;
                while (leftIdx < AList.size() && AList.get(leftIdx) == lv){
                    lc++;
                    leftIdx++;
                }
                while (rightIdx >=0 && BList.get(rightIdx) == rv){
                    rc++;
                    rightIdx--;
                }
                ans += lc*rc;
            }
            if(lv+rv > target) rightIdx--;
            if(lv+rv < target) leftIdx++;

        }
        System.out.println(ans);
    }

    private static void getSum(int sum, int startIdx, int idx, boolean[] check, int[] arr, ArrayList<Integer> list) {

        if(idx == arr.length){
            idx = 0;
        }
        list.add(sum);
        // 아직 안담은 피자 조건에대해서만 && 순환 방지함.
        if(check[idx] == false && sum <= target && idx != startIdx-1){
            check[idx] =true;
            getSum(sum+arr[idx] , startIdx, idx +1, check, arr,list);
        }else {
            return;
        }

    }
}