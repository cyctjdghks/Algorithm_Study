package coplit.dailyCoding.study.백준.week23;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_13144 {
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int[] arr = new int[N];	//수열 저장 배열
        HashSet<Integer> set = new HashSet<>();	//중복 방문 확인 HashSet<>()
        long result = 0;
        //입력값 저장!!
        for(int i=0;i<N;i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int start = 0;	//연속된 수 시작 Index 변수
        //수열 순차적으로 탐색!
        for(int i=0;i<N;i++){
            if(set.contains(arr[i])){		//중복된 수 탐색!
                //중복된 값이 없어질 때까지 땡기기!
                for(int j=start;j<i;j++){
                    result += i-j;	//땡겨져서 떨어지는 값 모든 경우 더하기!
                    start++;		//시작 Index 증가
                    if(arr[j] == arr[i])	//중복된값 찾았을 때
                        break;
                    set.remove(arr[j]);	//떨어진 값 미방문으로 변경
                }
            }else		//중복된 값이 아닐 때
                set.add(arr[i]);
        }
        //수열에 끝에 도착했을 때 현재 시작값 기준 모든 경우 구하기!
        for(int i=start;i<N;i++)
            result += N-i;	//N - i : 시작값 ~ 수열의 끝의 길이

        bw.write(String.valueOf(result));	//모든 경우 BufferedWriter 저장
        bw.flush();		//결과 출력
        bw.close();
        br.close();

    }
}
