import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static List<ArrayList<Integer>> nodeList;
    static int size;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        size = (int)Math.pow(2, n)-1;
        arr = new int[(int)Math.pow(2,n) - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        nodeList = new ArrayList<>();
        for(int i=0; i<n; i++) {
            nodeList.add(new ArrayList<>()) ;
        }

        func(0,size-1,0);

        for(int i=0; i<nodeList.size(); i++) {
            for(int nd : nodeList.get(i)) {
                System.out.print(nd+" ");
            }
            System.out.println();
        }
    }

    static void func(int start, int end, int depth) {
        if(start>end) return;
        int mid = (start+end)/2;

        nodeList.get(depth).add(arr[mid]);
        
        func(start,mid-1,depth+1);
        func(mid+1,end,depth+1);
    }
}