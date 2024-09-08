// Math.ceil(y1) = NaN일 떄,
// (double) Math.ceil(y1) = NaN -> NaN을 다시 double로 바꿔서 NaN이 된다.
// (long) Math.ceil(y1) = 0 -> NaN을 long 으로 형변환하면 0으로 취급하기 때문에

class Solution {
    public long solution(int r1, int r2) {
        // 1사분면 값 * 4
        // x=1부터 시작 (x=0 부분은 중복되는 부분이기 때문에)
        long result = 0;
        for(int x =1;x<= r2;x++){
            long y2 = (long) Math.sqrt(Math.pow(r2, 2) - Math.pow(x,2));
            double y1 = Math.sqrt(Math.pow(r1, 2) - Math.pow(x,2));

            result += (long) (y2 - (long) Math.ceil(y1) + 1);
        }
        return result * 4;
    }
}
