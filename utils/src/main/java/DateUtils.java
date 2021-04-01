import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SuppressWarnings("unused")
public class DateUtils {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter dtf_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final String YYYY_MM = "yyyy-MM";

    private static final ZoneId zoneId = ZoneId.of("Asia/Shanghai");

    public static final long ONE_MINUTE_MS = 60 * 1000;

    public static final long TEN_MINUTE_MS = 10 * ONE_MINUTE_MS;

    public static final long ONE_HOUR_MS = 60 * ONE_MINUTE_MS;

    public static final long ONE_DAY_MS = 24 * ONE_HOUR_MS;

    /**
     * 当天的开始时间
     * @return 开始时间的时间戳
     */
    public static long getBeginDateTime() {
        LocalDate nowDate = LocalDate.now(zoneId);//获取当前日期
        LocalDateTime beginDateTime = LocalDateTime.of(nowDate, LocalTime.MIN);//设置零点
        ZonedDateTime now = ZonedDateTime.of(beginDateTime, zoneId);
        return now.toInstant().toEpochMilli();
    }


    /**
     * 当天的开始时间
     * @return 开始时间的时间戳
     */
    public static long getBeginDateTime(long dateTime) {
        Instant instant = Instant.ofEpochMilli(dateTime);
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        LocalDate nowDate = zonedDateTime.toLocalDate();
        LocalDateTime beginDateTime = LocalDateTime.of(nowDate, LocalTime.MIN);//设置零点
        ZonedDateTime now = ZonedDateTime.of(beginDateTime, zoneId);
        return now.toInstant().toEpochMilli();
    }

    /**
     * 当天的结束时间
     * @return 结束时间的时间戳
     */
    public static long getEndDateTime() {
        LocalDate nowDate = LocalDate.now(zoneId);//获取当前日期
        LocalDateTime endDateTime = LocalDateTime.of(nowDate, LocalTime.MAX);
        ZonedDateTime now = ZonedDateTime.of(endDateTime, zoneId);
        return now.toInstant().toEpochMilli();
    }

    public static long getBetweenDateTime(long dateTime1, long dateTime2) {
        return dateTime1 - dateTime2;
    }

    /**
     * 当前时间的前一天
     */
    public static long getPreDateTime(long dateTime) {
        return dateTime - ONE_DAY_MS;
    }

    /**
     * 将时间戳转换成时间字符串
     * @param dateTime 时间戳
     * @return 时间字符串
     */
    public static String getDateTimeStr(long dateTime) {
        Instant instant = Instant.ofEpochMilli(dateTime);
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.format(dtf);
    }

    /**
     * 将时间戳转换成时间字符串
     * @param dateTime 时间戳
     * @return 时间字符串
     */
    public static String getDateStr(long dateTime) {
        Instant instant = Instant.ofEpochMilli(dateTime);
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.format(dtf_YYYY_MM_DD);
    }

    /**
     * 将 Date 装换成 时间戳
     * @param date date
     * @return 时间戳
     */
    public static long getDateTime(Date date) {
        return date.toInstant().toEpochMilli();
    }

    /**
     * 将 指定的时间字符串 转换成 时间戳 ms
     * @param dateTimeStr 时间字符串
     * @return 时间戳
     */
    public static long getDateTimeMs(String dateTimeStr) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, dtf);
        return ZonedDateTime.of(localDateTime, zoneId).toEpochSecond() * 1000;
    }

    /**
     * 将指定的时间字符串  转换为 Date
     * @param dateStr
     * @param formatStr
     * @return date
     */
    public static Date getDateStr(String dateStr, String formatStr) {
        if (dateStr == null) return null;
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {
       /* System.out.println(DateUtils.getBeginDateTime());
        System.out.println(DateUtils.getEndDateTime());

        System.out.println(DateUtils.getDateTimeStr(DateUtils.getBeginDateTime()));
        System.out.println(DateUtils.getDateTimeStr(DateUtils.getEndDateTime()));

        System.out.println(DateUtils.getDateTime(new Date()));
        System.out.println(DateUtils.getDateTimeMs("2019-05-10 00:00:00"));*/

        Date dateStr = DateUtils.getDateStr("1970-01-01 08:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateStr.getTime());
        System.out.println(dateStr);
        System.out.println(DateUtils.getDateTimeStr(0l));

        long l = dateStr.getTime();
        System.out.println("——————————————"+DateUtils.getDateTimeStr(l));
    }
}
