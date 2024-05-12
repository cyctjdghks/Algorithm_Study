import java.util.*;

public class BOJ_19942 {

    static int N; // 식재료 개수
    static int[] nutrient; // 단백질, 지방, 탄수화물, 비타민의 최소 영양성분
    static Material[] materials; // 식재료의 단백질, 지방, 탄수화물, 비타민과 가격
    static int minPrice = Integer.MAX_VALUE;
    static List<Integer> bestCombination = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        nutrient = new int[4];

        for (int i = 0; i < 4; i++) {
            nutrient[i] = sc.nextInt();
        }

        materials = new Material[N];
        for (int i = 0; i < N; i++) {
            int pi = sc.nextInt();
            int fi = sc.nextInt();
            int si = sc.nextInt();
            int vi = sc.nextInt();
            int ci = sc.nextInt();
            materials[i] = new Material(pi, fi, si, vi, ci);
        }

        dfs(0, new Material(0, 0, 0, 0, 0), new ArrayList<>());

        if (minPrice == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minPrice);
            for (int index : bestCombination) {
                System.out.print((index + 1) + " ");
            }
            System.out.println();
        }
    }

    private static void dfs(int index, Material current, List<Integer> combination) {
        if (index == N) {
            if (check(current)) {
                if (current.ci < minPrice) {
                    minPrice = current.ci;
                    bestCombination = new ArrayList<>(combination);
                } else if (current.ci == minPrice) {
                    if (bestCombination == null || isBetterCombination(combination, bestCombination)) {
                        bestCombination = new ArrayList<>(combination);
                    }
                }
            }
            return;
        }

        dfs(index + 1, current, combination);

        combination.add(index);
        Material nextMaterial = new Material(
                current.pi + materials[index].pi,
                current.fi + materials[index].fi,
                current.si + materials[index].si,
                current.vi + materials[index].vi,
                current.ci + materials[index].ci
        );
        dfs(index + 1, nextMaterial, combination);
        combination.remove(combination.size() - 1);
    }

    private static boolean isBetterCombination(List<Integer> newComb, List<Integer> bestComb) {
        for (int i = 0; i < Math.min(newComb.size(), bestComb.size()); i++) {
            if (newComb.get(i) != bestComb.get(i)) {
                return newComb.get(i) < bestComb.get(i);
            }
        }
        return newComb.size() < bestComb.size();
    }

    private static boolean check(Material cur) {
        return cur.pi >= nutrient[0] && cur.fi >= nutrient[1] &&
                cur.si >= nutrient[2] && cur.vi >= nutrient[3];
    }

    public static class Material {
        int pi, fi, si, vi, ci;

        public Material(int pi, int fi, int si, int vi, int ci) {
            this.pi = pi;
            this.fi = fi;
            this.si = si;
            this.vi = vi;
            this.ci = ci;
        }
    }
}
