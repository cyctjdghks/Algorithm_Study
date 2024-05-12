import java.util.*;

public class BOJ_14405 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String S = sc.next();
        char[] arr = S.toCharArray();

        if (isPronounceable(arr)) System.out.println("YES");
        else System.out.println("NO");
    }

    private static boolean isPronounceable(char[] arr) {

        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case 'p':
                    if (i + 1 < arr.length && arr[i + 1] == 'i') i++;
                    else return false;
                    break;
                case 'k':
                    if (i + 1 < arr.length && arr[i + 1] == 'a') i++;
                    else return false;
                    break;
                case 'c':
                    if (i + 2 < arr.length && arr[i + 1] == 'h' && arr[i + 2] == 'u') i += 2;
                    else return false;
                    break;
                default:
                    return false;
            }
        }


        return true;
    }
}
