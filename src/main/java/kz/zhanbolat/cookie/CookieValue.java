package kz.zhanbolat.cookie;

import javax.servlet.http.Cookie;

public class CookieValue {
    private String name;
    private String value;
    private int maxAge;
    private String domain;
    private boolean secure;
    private boolean httpOnly;
    private String path;
    private int version;

    private CookieValue(CookieBuilder builder) {
        this.domain = builder.domain;
        this.name = builder.name;
        this.httpOnly = builder.httpOnly;
        this.maxAge = builder.maxAge;
        this.path = builder.path;
        this.secure = builder.secure;
        this.value = builder.value;
        this.version = builder.version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public boolean isHttpOnly() {
        return httpOnly;
    }

    public void setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public static CookieBuilder init() {
        return new CookieBuilder();
    }

    public static CookieValue valueOf(Cookie cookie) {
        return CookieValue.init()
                .setName(cookie.getName())
                .setDomain(cookie.getDomain())
                .setHttpOnly(cookie.isHttpOnly())
                .setMaxAge(cookie.getMaxAge())
                .setPath(cookie.getPath())
                .setSecure(cookie.getSecure())
                .setValue(cookie.getValue())
                .setVersion(cookie.getVersion())
                .build();
    }

    public String toHeader() {
        return new StringBuilder()
                .append(name)
                .append(": ")
                .append(value)
                .append("; ")
                .append(domain)
                .append("; ")
                .append(maxAge)
                .append("; ")
                .append(path)
                .append("; ")
                .append(secure ? "Secure" : "")
                .append("; ")
                .append(httpOnly ? "HttpOnly" : "")
                .toString();
    }

    public static class CookieBuilder {
        private String name;
        private String value;
        private int maxAge;
        private String domain;
        private boolean secure;
        private boolean httpOnly;
        private String path;
        private int version;

        public CookieBuilder() {

        }

        public CookieBuilder setName(String name) {
            this.name = name;

            return this;
        }

        public CookieBuilder setValue(String value) {
            this.value = value;

            return this;
        }

        public CookieBuilder setMaxAge(int maxAge) {
            this.maxAge = maxAge;

            return this;
        }

        public CookieBuilder setDomain(String domain) {
            this.domain = domain;

            return this;
        }

        public CookieBuilder setSecure(boolean secure) {
            this.secure = secure;

            return this;
        }

        public CookieBuilder setHttpOnly(boolean httpOnly) {
            this.httpOnly = httpOnly;

            return this;
        }

        public CookieBuilder setPath(String path) {
            this.path = path;

            return this;
        }

        public CookieBuilder setVersion(int version) {
            this.version = version;

            return this;
        }

        public CookieValue build() {
            return new CookieValue(this);
        }
    }
}
