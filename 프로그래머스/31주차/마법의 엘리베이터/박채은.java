class Solution {
    public int solution(int storey) {
        int result = 0;
        while(storey > 0){
            int n = storey % 10;
            storey /= 10;
            if(n > 5){
                result += (10 - n);
                storey+=1;
            } else if (n < 5) {
                result += n;
            }
            else{ // n==5
                if(storey % 10 >= 5){
                    result += 5;
                    storey+=1;
                }
                else{
                    result += 5;
                }
            }
        }
        return result;
    }
}