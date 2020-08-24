package kz.zhanbolat.cookie.processor.impl;

import kz.zhanbolat.cookie.CookieValue;
import kz.zhanbolat.cookie.processor.CookieProcessor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HttpCookieProcessor implements CookieProcessor {
    @Override
    public List<CookieValue> readCookieValues(HttpServletRequest request) {
        return Stream.of(request.getCookies()).map(CookieValue::valueOf).collect(Collectors.toList());
    }

    @Override
    public void writeCookieValue(CookieValue cookieValue, HttpServletResponse response) {
        response.setHeader("Set-Cookie", cookieValue.toHeader());
    }
}
