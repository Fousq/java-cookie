package kz.zhanbolat.cookie;

import java.util.Objects;

public class CookieValueValidator {
    private static final String BLANK_STRING = " ";

    public static boolean isStringValid(String string) {
        return !(Objects.isNull(string)
                || Objects.equals(string, BLANK_STRING)
                || string.isEmpty());
    }
}
