class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i=0;i<numbers.length;i++){
            // 1. 10진수 -> 2진수
            String s = getBinary(numbers[i]);

            // 2. 포화이진트리로 변경
            String fullString = getFullBinary(s);

            // 3. 이진트리인지 확인
            if(isBinaryTree(fullString) == -1){
                answer[i] = 0;
            }
            else{
                answer[i] = 1;
            }
        }

        return answer;
    }

    public int isBinaryTree(String numbers){
        int len = numbers.length();
        if(len == 0) return 0;
        if(len == 1) return numbers.charAt(0) - '0';

        int root = len / 2;
        String leftNode = numbers.substring(0, root);
        int result1 = isBinaryTree(leftNode);
        if(result1 == -1){
            return -1;
        }

        String rightNode = numbers.substring(root+1);
        int result2 = isBinaryTree(rightNode);
        if(result2 == -1){
            return -1;
        }

        int mid = numbers.charAt(root) - '0';
        // 부모는 없는데, 자식이 존재하는 경우
        if(mid == 0 && result1 + result2 > 0){
            return -1;
        } else if (result1 + result2 + mid == 0) {
            return 0;
        } else{
            return 1;
        }
    }

    public String getFullBinary(String numbers){
        int numLen = numbers.length();
        int curLen = 1;
        int levelNode = 1;
        while(curLen < numLen){
            levelNode *= 2; // level에 있는 노드 수
            curLen += levelNode;
        }

        int offset = curLen - numLen;
        return "0".repeat(offset) + numbers;
    }
    public String getBinary(long numbers){
        StringBuilder sb = new StringBuilder();
        while(numbers > 0){
            if(numbers % 2 == 0){
                sb.insert(0, "0");
            }else{
                sb.insert(0, "1");
            }
            numbers /= 2;
        }
        return sb.toString();
    }
}