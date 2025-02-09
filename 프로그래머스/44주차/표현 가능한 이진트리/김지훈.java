class Solution {
    public int treeLength;
    public int result;

    public void validateBinaryTree(boolean[] target, int start, int end, boolean isEnd) {
        int mid = (start + end) / 2;
        boolean current = target[mid];
        if (isEnd && current) {
            result = 0;
            return;
        }
        if (start != end) {
            validateBinaryTree(target, start, mid-1, !current);
            validateBinaryTree(target, mid+1, end, !current);
        }
    }

    private boolean[] makeTarget(long number) {
        int binaryLength = (int) Math.floor(Math.log(number) / Math.log(2)) + 1;

        int treeHeight = 1;
        treeLength = 0;
        while (treeLength < binaryLength) {
            treeLength = (int) Math.pow(2, treeHeight++) - 1;
        }

        boolean[] target = new boolean[treeLength];
        int i = treeLength - 1;
        while(true) {
            long div = number / 2;
            long mod = number % 2;
            number = div;
            target[i--] = (mod == 1);
            if (div == 1) {
                target[i] = true;
                break;
            } else if (div == 0) {
                break;
            }
        }
        return target;
    }
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length ; i++) {
            result = 1;
            boolean[] target = makeTarget(numbers[i]);
            validateBinaryTree(target, 0, treeLength-1, false);
            answer[i] = result;
        }
        return answer;
    }
}