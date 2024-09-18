import java.util.*;

public class Solution {
    public String[] solution(String[] expressions) {
        List<String> answer = new ArrayList<>();
        List<String> hints = new ArrayList<>();
        List<Integer> answerFormat = new ArrayList<>();
        int maxFormat = 0;

        // 힌트와 최대 기수 계산
        for (String e : expressions) {
            String[] parts = e.split(" ");
            String num1 = parts[0];
            String func = parts[1];
            String num2 = parts[2];
            String ans = parts[4];

            maxFormat = Math.max(maxFormat, getMaxFormat(num1));
            maxFormat = Math.max(maxFormat, getMaxFormat(num2));

            if (!ans.equals("X")) {
                hints.add(e);
                maxFormat = Math.max(maxFormat, getMaxFormat(ans));
            } else {
                answer.add(e);
            }
        }

        // 가능한 기수 찾기
        for (int n = maxFormat + 1; n < 10; n++) {
            boolean valid = true;
            for (String h : hints) {
                String[] parts = h.split(" ");
                String num1 = parts[0];
                String func = parts[1];
                String num2 = parts[2];
                String ans = parts[4];

                int num1Dec = NToTen(n, num1);
                int num2Dec = NToTen(n, num2);
                int ansDec = NToTen(n, ans);

                if (func.equals("+") && num1Dec + num2Dec != ansDec) {
                    valid = false;
                    break;
                }
                if (func.equals("-") && num1Dec - num2Dec != ansDec) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                answerFormat.add(n);
            }
        }

        // 정답 계산
        for (int i = 0; i < answer.size(); i++) {
            String[] parts = answer.get(i).split(" ");
            String num1 = parts[0];
            String func = parts[1];
            String num2 = parts[2];
            String ans = parts[4];

            Set<String> possibleAnswers = new HashSet<>();
            for (int base : answerFormat) {
                int num1Dec = NToTen(base, num1);
                int num2Dec = NToTen(base, num2);
                String result = "";

                if (func.equals("+")) {
                    result = TenToN(base, num1Dec + num2Dec);
                }
                if (func.equals("-")) {
                    result = TenToN(base, num1Dec - num2Dec);
                }

                possibleAnswers.add(result);
            }

            if (possibleAnswers.size() == 1) {
                answer.set(i, String.join(" ", num1, func, num2, "=", possibleAnswers.iterator().next()));
            } else {
                answer.set(i, String.join(" ", num1, func, num2, "=", "?"));
            }
        }

        return answer.toArray(new String[0]);
    }

    private int getMaxFormat(String num) {
        int max = 0;
        for (char c : num.toCharArray()) {
            max = Math.max(max, Character.getNumericValue(c));
        }
        return max;
    }

    private int NToTen(int base, String num) {
        if (num.length() == 1) return Integer.parseInt(num);

        int decimal = 0;
        for (int i = 0; i < num.length(); i++) {
            decimal += Character.getNumericValue(num.charAt(i)) * Math.pow(base, num.length() - 1 - i);
        }
        return decimal;
    }

    private String TenToN(int base, int num) {
        if (num == 0) return "0";

        StringBuilder answer = new StringBuilder();
        for (int idx = 2; idx >= 0; idx--) {
            int div = num / (int) Math.pow(base, idx);
            if (answer.length() > 0 || div > 0) {
                answer.append(div);
            }
            num = num % (int) Math.pow(base, idx);
        }
        return answer.toString();
    }
}
