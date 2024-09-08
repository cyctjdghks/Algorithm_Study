import java.util.*;

class Solution {
    long[] sumA, sumB;
    public long solution(int[] sequence) {
        int lastIdx = sequence.length-1;
        init(sequence);
        for(int i=1; i<lastIdx+1; i++){
            sumA[i] += (sumA[i-1] > 0) ? sumA[i-1] : 0;
            sumB[i] += (sumB[i-1] > 0) ? sumB[i-1] : 0;
        }
        Arrays.sort(sumA);
        Arrays.sort(sumB);
        
        return (sumA[lastIdx] > sumB[lastIdx]) ? sumA[lastIdx] : sumB[lastIdx];
    }
    
    private void init(int[] array){
        sumA = new long[array.length];
        sumB = new long[array.length];
        for(int i=0; i<sumA.length; i++){
            if(i%2==0){
                sumA[i] = array[i];
                sumB[i] = -array[i];
            }else{
                sumB[i] = array[i];
                sumA[i] = -array[i];
            }
        }
    }
}
