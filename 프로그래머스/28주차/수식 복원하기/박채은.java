import java.util.*;

class Solution {
    ArrayList<String[]> al = new ArrayList<>();
    public String[] solution(String[] expressions) {
        // 1. 수식 분해 -> [a,b,c,연산]으로
        int max = 0;
        for(String ex : expressions){
            String[] spl = ex.split(" ");
            String[] s = new String[4];
            s[0] = spl[0];
            s[1] = spl[2];
            s[2] = spl[4];
            s[3] = spl[1];
            al.add(s);
            max = findMaxValue(s[0], s[1], s[2], max);
        }

        // 2. 수식 중에 가장 큰 값 찾기 (max + 1이 최소 진법이 된다.)
        int minType = 2;
        if(max == 9) minType = 9;
        if(max < 9) minType = max +1;

        // 3. minType ~ 9진수 중에 가능한 진법을 구하기
        ArrayList<Integer> type = new ArrayList<>();
        for(int j=minType;j<=9;j++){
            boolean check = true;
            for(int i=0;i<al.size();i++){
                String[] ex = al.get(i);
                if(ex[2].equals("X")){
                    continue;
                }

                int conv_a = convertATo10(j, ex[0]);
                int conv_b = convertATo10(j, ex[1]);
                int conv_c = convertATo10(j, ex[2]);

                if(cal(conv_a, conv_b, ex[3]) != conv_c){
                    check = false;
                }
            }
            if(check == true){
                type.add(j);
            }
        }

        // 4. X의 값 구하기
        ArrayList<String> answer = new ArrayList<>();
        for(int i=0;i<al.size();i++){
            String[] ex = al.get(i);
            if(ex[2].equals("X")){
                ArrayList<Integer> al = new ArrayList<>(); // 가능한 X 값들
                for(int j=0;j<type.size();j++){
                    int t = type.get(j);

                    int conv_a = convertATo10(t, ex[0]);
                    int conv_b = convertATo10(t, ex[1]);
                    int result_c = cal(conv_a, conv_b, ex[3]);

                    int conv_c = convert10ToA(t, result_c);
                    al.add(conv_c);
                }

                boolean flag = true;
                int compareNum = al.get(0);
                for(int j=1;j<al.size();j++){
                    if(compareNum != al.get(j)){
                        flag = false;
                        break;
                    }
                }
                // X의 값을 수정한다.
                if(flag){
                    expressions[i] = expressions[i].replace("X", compareNum+"");
                }
                else{ // X를 ?로 수정한다.
                    expressions[i] = expressions[i].replace("X", "?");
                }
                answer.add(expressions[i]);
            }
        }

        return answer.toArray(new String[0]);
    }
    // 연산자에 따라 값을 계산
    public int cal(int a, int b, String op){
        if(op.equals("+")){
            return a+b;
        }else {
            return a-b;
        }
    }

    // a를 type -> 10진수로 변경
    public int convertATo10(int type, String a){
        if(a.length() == 1) return Integer.parseInt(a);

        int num = 0;
        for(int i=0;i<a.length();i++){
            int digit = Character.getNumericValue(a.charAt(i));
            num += digit * Math.pow(type, a.length() -1 -i);
        }
        return num;
    }

    public int convert10ToA(int type, int a){
        StringBuilder sb = new StringBuilder();

        for(int i=2;i>=0;i--){
            int power = (int) Math.pow(type, i);
            int k = a / power;
            sb.append(k);
            a = a % power;
        }

        return Integer.parseInt(sb.toString());
    }

    public int findMaxValue(String a, String b, String c, int max){
        int numA = Integer.parseInt(a);
        int numB = Integer.parseInt(b);
        int numC = (c.equals("X")) ? 1 : Integer.parseInt(c);

        int maxValue = Math.max(max, numA);
        maxValue = Math.max(maxValue, numB);
        maxValue = Math.max(maxValue, numC);

        return maxValue;
    }
}