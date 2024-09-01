import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static String str = "Messi Gimossi";
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        int one = 5;
        int two = 13;
        list.add(one);
        list.add(two);
        int size=0;
        int idx=2;
        while(true){
            if(size > n) break;
            size = one + two + 1;
            one = two;
            two = size;
            idx++;
            list.add(size);
        }

        int result = recur(idx,n);

        if (result == -1 || result == 6)
        {
            System.out.println("Messi Messi Gimossi");
        }
        else
            System.out.println(str.charAt(result - 1));
    }
    static int recur(int idx, int num){
        if(num <= list.get(1)){
            return num;
        }
        if(num <= list.get(idx-1)){
            return recur(idx-1,num);
        }
        else if(num == list.get(idx-1) + 1){
            return -1;
        }
        else{
            return recur(idx-2,num-list.get(idx-1) - 1);
        }
    }


}
