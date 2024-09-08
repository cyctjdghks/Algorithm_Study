// 일치하는 음악이 여러 개 - 재생기간이 긴 음악을 선택 > 먼저 입력된 음악
// C, C#, D, D#, E, F, F#, G, G#, A, A#, B

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        int maxPlayTime = 0;

        // # 붙은 음 치환
        m = changeRhythm(m);

        for(String s: musicinfos){
            String[] info = s.split(",");
            String start = info[0];
            String end = info[1];
            String title = info[2];
            String rhythm = info[3];

            // # 붙은 음 치환
            rhythm = changeRhythm(rhythm);

            // 총 재생 시간 (분으로 변경)
            String[] st = start.split(":");
            String[] e = end.split(":");

            int startTime = (Integer.parseInt(st[0]) * 60) + Integer.parseInt(st[1]);
            int endTime = (Integer.parseInt(e[0]) * 60) + Integer.parseInt(e[1]);

            int playTime = endTime - startTime;

            String newRhy = "";
            // 음악의 길이 > 음악 재생 시간 (처음 ~ 재생기간)
            if(rhythm.length() >= playTime){
                // 음악의 길이를 재생 시간만큼 줄어줌
                newRhy = rhythm.substring(0, playTime);
            }
            else{ // 음악 길이 > 재생 시간
                // 음악 길이만큼 반복시켜준다.
                // 몇 번 반복해야하는지
                int n = playTime / rhythm.length();
                int k = playTime % rhythm.length();
                newRhy = rhythm.repeat(n);
                newRhy += rhythm.substring(0, k);
            }

            // 일치하는지 확인
            if(newRhy.contains(m) && maxPlayTime < playTime){
                answer = title;
                maxPlayTime = playTime;
            }
        }

        if(answer.equals("")){
            return "(None)";
        }

        return answer;
    }

    public String changeRhythm(String r){
        r = r.replaceAll("C#" , "H");
        r = r.replaceAll("D#" , "I");
        r = r.replaceAll("F#" , "J");
        r = r.replaceAll("G#" , "K");
        r = r.replaceAll("A#" , "L");
        r = r.replaceAll("B#" , "M");

        return r;
    }
}