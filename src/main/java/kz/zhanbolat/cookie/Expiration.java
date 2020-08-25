package kz.zhanbolat.cookie;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class Expiration {
    private final LocalDateTime dateTime;

    private Expiration(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static Expiration minutes(long minutes) {
        return new Expiration(LocalDateTime.now().plusMinutes(minutes));
    }

    public static Expiration hours(long hours) {
        return new Expiration(LocalDateTime.now().plusHours(hours));
    }

    public static Expiration days(int days) {
        return new Expiration(LocalDateTime.now().plusDays(days));
    }

    public static Expiration date(Date date) {
        return new Expiration(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    public static Expiration localDate(LocalDate localDate) {
        return new Expiration(localDate.atStartOfDay());
    }

    public static Expiration localDateTime(LocalDateTime localDateTime) {
        return new Expiration(localDateTime);
    }

    public String toFormattedString() {
        return dateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
