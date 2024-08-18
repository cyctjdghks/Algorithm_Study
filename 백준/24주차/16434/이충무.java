import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,atk;

    static Room[] arr;
    static class Room {
        int t;
        int a;
        int h;

        public Room(int t, int a, int h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        atk = Integer.parseInt(st.nextToken());

        arr= new Room[n];

        long start = 1;
        long end = 1;

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            arr[i]= new Room(t,a,h);
            if(t == 1) end += ((long)a * h);
        }

        while(start <= end){
            long mid = (start+end)/2;

            boolean flag = travel(mid);

            if(flag){
                end = mid-1;
            }
            else{
                start = mid+1;
            }


        }
        System.out.println(start);
    }
    static boolean travel(long maxHp){
        long curHp = maxHp;
        long curAtk = atk;
        //t a h
        for(int i=0;i<n;i++){
            long rAtk = arr[i].a;
            long rHp = arr[i].h;
            if(arr[i].t == 1) {
                long tmp;
                if(rHp % curAtk == 0) tmp = rHp/curAtk -1;
                else tmp = rHp/curAtk;

                curHp-= (long) rAtk *tmp;

                if(curHp <= 0) {
                    return false;
                }
            }
            else if(arr[i].t == 2){
                curHp = Math.min(maxHp, curHp+rHp);
                curAtk+=rAtk;
            }
        }

        return true;
    }
}