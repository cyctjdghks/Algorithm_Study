import java.util.*;

class Solution {
    Map<String, String> people = new HashMap<>();
    Map<String, Integer> money = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for (int i = 0; i < enroll.length; i++) {
            people.put(enroll[i], referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            share(seller[i], amount[i] * 100);
        }

        int[] result = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            result[i] = money.getOrDefault(enroll[i], 0);
        }

        return result;
    }

    void share(String person, int sales) {
        int give = sales / 10;
        money.put(person, money.getOrDefault(person, 0) + sales - give);

        if (give > 0 && people.containsKey(person)) {
            share(people.get(person), give);
        }
    }
}