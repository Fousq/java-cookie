package kz.zhanbolat.cookie.processor;

import kz.zhanbolat.cookie.CookieValue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CookieProcessor {
    List<CookieValue> readCookieValues(HttpServletRequest request);
    void writeCookieValue(CookieValue cookieValue, HttpServletResponse response);
}
