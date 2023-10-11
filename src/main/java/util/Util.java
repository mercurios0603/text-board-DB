package util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    // util 데이터를 표현하는 것이 아님.
    // 유틸리티 클래스는 static을 붙여서 사용한다. 그러면 new가 필요없음.
    public static String getCurrentTime() {

        ZoneId seoulZone = ZoneId.of("Asia/Seoul");
        ZonedDateTime now = LocalDateTime.now().atZone(seoulZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        String writingtime = now.format(formatter);

        return writingtime;
    }

}
