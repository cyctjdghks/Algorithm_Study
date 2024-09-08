import java.util.*;

class Solution {
        // 조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환한다.
        // 멜로디 C, C#, D, D#, E, F, F#, G, G#, A, A#, B 12개
        // 1 <= m <= 1439
        // musicinfos.length <= 100
        // , 로 구분
        // 시간은 24시간 HH:MM
        // 음악 제목은 1 ~ 64
        // 악보 정보는 1 ~ 1439
        // 없으면 (None)
    private String convertMelody(String melody) {
        // '#'이 포함된 음을 소문자로 변환하여 처리
        return melody.replaceAll("C#", "c").replaceAll("D#", "d").replaceAll("F#", "f").replaceAll("G#", "g").replaceAll("A#", "a").replaceAll("B#", "b");
    }

    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxTime = 0;

        m = convertMelody(m); // 사용자가 기억하는 멜로디도 동일하게 변환

        for (String info : musicinfos) {
            String[] parts = info.split(",");
            String[] start = parts[0].split(":");
            String[] end = parts[1].split(":");
            int startMin = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            int endMin = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
            int playTime = endMin - startMin;

            String title = parts[2];
            String melody = convertMelody(parts[3]);

            // 재생된 멜로디 생성
            StringBuilder playedMelody = new StringBuilder();
            for (int i = 0; i < playTime; i++) {
                playedMelody.append(melody.charAt(i % melody.length()));
            }

            // 멜로디가 포함되어 있는지 확인
            if (playedMelody.toString().contains(m) && playTime > maxTime) {
                answer = title;
                maxTime = playTime;
            }
        }

        return answer;
    }
}
