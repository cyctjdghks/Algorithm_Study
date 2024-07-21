package coplit.dailyCoding.study.백준;

import java.io.*;
import java.util.*;
class BOJ_1450 {
    static long cnt = 0;
    static long binary_Search(ArrayList<Long> lst, long x){
        if (x < 0) return 0;
        else {
            int l = 0;
            int r = lst.size() - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (lst.get(mid) > x)
                    r = mid - 1;
                else
                    l = mid + 1;
            }
            return l;
        }
    }

    static void count(ArrayList<Long> lst1, ArrayList<Long> lst2, int c){
        for (int i = 0; i < lst1.size(); i++)
            cnt += binary_Search( lst2, c - lst1.get(i));
    }
    static void dfs(int[] arr, ArrayList<Long> lst, long sum, int p, int x){
        if (p != x){
            lst.add(sum + arr[p]);
            dfs(arr, lst, sum + arr[p], p + 1, x);
            dfs(arr, lst, sum, p + 1, x);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int a = n / 2;
        int b = n - a;
        int[] arr1 = new int[a];
        ArrayList<Long> lst1 = new ArrayList<>();
        int[] arr2 = new int[b];
        ArrayList<Long> lst2 = new ArrayList<>();
        for (int i = 0; i < a; i++)
            arr1[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < b; i++)
            arr2[i] = Integer.parseInt(st.nextToken());
        lst1.add(0L);
        lst2.add(0L);
        dfs(arr1, lst1, 0, 0, a);
        dfs(arr2, lst2, 0, 0, b);
        Collections.sort(lst1);
        Collections.sort(lst2);
        count(lst1, lst2, c);
        System.out.println(cnt);
    }
}
