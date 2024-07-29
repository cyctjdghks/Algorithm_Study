import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, m;
    static int[] arr;
    static char[] str;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        m = n/2;
        arr = new int[m+1];
        str = new char[m];
        for(int i=0; i<n; i++){
            if(i%2==0){
                arr[i/2] = Integer.parseInt(String.valueOf(s.charAt(i)));
            }
            else{
                str[i/2] = s.charAt(i);
            }
        }
        result = Integer.MIN_VALUE;
        dfs(arr[0],0);

        System.out.println(result);

    }

    private static int execute(char op, int n1, int n2){
        if(op=='+'){
            return n1+n2;
        }
        else if(op=='-'){
            return n1-n2;
        }
        else{
            return n1*n2;
        }
    }

    private static void dfs(int tempResult,int index) {
        if(index>=m){
            result = Math.max(tempResult,result);
            return;
        }

        int tempResult1 = execute(str[index], tempResult, arr[index+1]);
        dfs(tempResult1, index+1);

        if(index+1 <m){
            int tempResult2 = execute(str[index+1],arr[index+1],arr[index+2]);
            dfs(execute(str[index],tempResult,tempResult2),index+2);
        }
    }
}