import java.util.Scanner;

public class BOJ_16434 {

    static int N;
    static long ATK;
    static long[][] rooms;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        ATK = sc.nextLong();

        rooms = new long[N][3];

        for (int i = 0; i < N; i++) {
            rooms[i][0] = sc.nextLong(); // t
            rooms[i][1] = sc.nextLong(); // a
            rooms[i][2] = sc.nextLong(); // h
        }

        System.out.println(findMinHP());
    }

    private static long findMinHP() {
        long left = 1;
        long right = 123_456L * 1_000_000L * 1_000_000L;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (isClearable(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean isClearable(long maxHP) {
        long currentHP = maxHP;
        long currentATK = ATK;

        for (int i = 0; i < N; i++) {
            long type = rooms[i][0];
            long atkOrHeal = rooms[i][1];
            long hpOrMonster = rooms[i][2];

            if (type == 1) { // Monster room
                long attacksRequired = (hpOrMonster + currentATK - 1) / currentATK;
                currentHP -= (attacksRequired - 1) * atkOrHeal;
                if (currentHP <= 0) return false;
            } else if (type == 2) { // Potion room
                currentATK += atkOrHeal;
                currentHP = Math.min(maxHP, currentHP + hpOrMonster);
            }
        }

        return true;
    }
}
