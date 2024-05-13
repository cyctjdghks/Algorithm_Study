import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int minVal[];
    static int val[][];
    static int[] selection;
    static int minPrice = Integer.MAX_VALUE;
    static ArrayList<String> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        N = Integer.parseInt(st.nextToken());

        minVal = new int[4];
        st = new StringTokenizer(br.readLine());

        for(int i=0;i<4;i++){
            minVal[i] = Integer.parseInt(st.nextToken());
        }

        val = new int[N][5];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<5;j++){
                val[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=N;i++){
            selection = new int[i];
            select(0, 0, i);
        }

        if(minPrice == Integer.MAX_VALUE) System.out.println(-1);
        else{
            Collections.sort(list);
            System.out.println(minPrice);
            System.out.println(list.get(0));
        }
    }

    // nCr 조합 선택
    static void select(int start, int index, int r){

        if(index == r){
            // 조합으로 값 구하기
            getValue(r);
            return;
        }
        for(int i=start;i<N;i++){
//            System.out.println("r= "+r);
//            System.out.println("start="+start+", index="+index+", i=" + i);
//            System.out.println("-----------------------");
            selection[index] = i;
            select(i+1, index +1,r);
        }
    }


    static void getValue(int r){
        int totalNutrition[] = new int[5];
        for(int i=0;i<r;i++){
            for(int j=0;j<5;j++){
                totalNutrition[j] += val[selection[i]][j];
            }
        }

        // 최솟값을 만족하는지 확인
        for(int i=0;i<4;i++){
            if(totalNutrition[i] < minVal[i]){
                return;
            }
        }
        // 최솟값 갱신
        if(minPrice >= totalNutrition[4]){
            if(minPrice > totalNutrition[4]){ // 같은 값이 없기 때문에 지워도 된다.
                list.clear();
            }
            minPrice = totalNutrition[4];
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<r;i++){
                sb.append((selection[i]+1) +" ");
            }

            list.add(sb.toString());
        }
    }
}