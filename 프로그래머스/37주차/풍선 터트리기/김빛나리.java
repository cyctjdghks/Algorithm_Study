class Solution {
    public int solution(int[] a) {
        if(a.length == 1) return 1;
        
        int left = a[0], right = a[a.length-1];
        // a의 길이가 2이상일 경우, 양끝에 숫자는 무조건 반환 가능
        int answer = 2;
        
        for(int i=1;i<a.length-1;i++) {
            // left보다 작을 경우, 터트리기 가능
            if(left > a[i]) {
                left = a[i];
                answer++;
            }
            
            // right보다 작을 경우, 터트리기 가능 
            // 오른쪽에서부터 왼쪽으로 이동
            if(right > a[a.length-1-i]) {
                right = a[a.length-1-i];
                answer++;
            }
            
            // 두 수가 같을 경우, 중복 -> -1
            if(left == right) {
                answer--;
                break;
            }
        }
        
        return answer;
    }
}