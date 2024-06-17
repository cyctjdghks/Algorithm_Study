import java.util.Scanner;

public class BOJ_2852{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int teamA = 0;
        int teamATime = 0;
        int teamB = 0;
        int teamBTime = 0;
        int now = 0;
        int fullTime = convertToTime("48:00");


        for (int i = 0; i < N; i++) {
            int winTeam = sc.nextInt();
            String inputTime = sc.next();
            int time = convertToTime(inputTime);

            // 시간 계산
            if (teamA > teamB) {
                teamATime += time - now;
            } else if (teamA < teamB) {
                teamBTime += time - now;
            }

            // 승자 기록 및 시간 기록
            switch (winTeam) {
                case 1:
                    teamA++;
                    break;
                case 2:
                    teamB++;
                    break;
                default:
                    break;
            }
            now = time;

        }

        // 시간 계산
        if (teamA > teamB) {
            teamATime += fullTime - now;
        } else if (teamA < teamB) {
            teamBTime += fullTime - now;
        }

        System.out.println(convertToString(teamATime));
        System.out.println(convertToString(teamBTime));
    }

    private static int convertToTime(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return hours * 60 + minutes;
    }

    public static String convertToString(int totalMinutes) {
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;

        return String.format("%02d:%02d", hours, minutes);
    }
}
