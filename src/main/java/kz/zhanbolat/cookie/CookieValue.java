package kz.zhanbolat.cookie;

import javax.servlet.http.Cookie;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class CookieValue {
    private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
    private static final ResourceBundle lStrings = ResourceBundle.getBundle( LSTRING_FILE );

    private String name;
    private String value;
    private int maxAge;
    private String domain;
    private boolean secure;
    private boolean httpOnly;
    private String path;
    private int version;
    private String sameSite;
    private Expiration expire;

    private CookieValue(CookieBuilder builder) {
        this.domain = builder.domain;
        this.name = builder.name;
        this.httpOnly = builder.httpOnly;
        this.maxAge = builder.maxAge;
        this.path = builder.path;
        this.secure = builder.secure;
        this.value = builder.value;
        this.version = builder.version;
        this.sameSite = builder.sameSite;
        this.expire = builder.expire;
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

    public String getSameSite() {
        return sameSite;
    }

    public void setSameSite(String sameSite) {
        this.sameSite = sameSite;
    }

    public Expiration getExpire() {
        return expire;
    }

    public void setExpire(Expiration expire) {
        this.expire = expire;
    }

    public static CookieBuilder init(String name, String value) {
        return new CookieBuilder().setName(name).setValue(value);
    }

    public static CookieValue valueOf(Cookie cookie) {
        return CookieValue.init(cookie.getName(), cookie.getValue())
                .setDomain(cookie.getDomain())
                .setHttpOnly(cookie.isHttpOnly())
                .setMaxAge(cookie.getMaxAge())
                .setPath(cookie.getPath())
                .setSecure(cookie.getSecure())
                .setVersion(cookie.getVersion())
                .build();
    }

    public String toHeader() {
        StringBuilder header = new StringBuilder();
        header.append(name).append("=").append(value);
        if (CookieValueValidator.isStringValid(domain)) {
            header.append("; Domain=").append(domain);
        }
        if (Objects.nonNull(expire)) {
            header.append("; Expires=").append(expire);
        }
        if (maxAge > 0) {
            header.append("; MaxAge=").append(maxAge);
        }
        if (CookieValueValidator.isStringValid(path)) {
            header.append("; Path=").append(path);
        }
        if (secure) {
            header.append("; Secure");
        }
        if (httpOnly) {
            header.append("; HttpOnly");
        }
        if (CookieValueValidator.isStringValid(sameSite)) {
            header.append("; SameSite=").append(sameSite);
        }
        return header.toString();
    }

    public static class CookieBuilder {
        private String name;
        private String value;
        private int maxAge;
        private String domain;
        private boolean secure = true;
        private boolean httpOnly = true;
        private String path;
        private int version = 1;
        private String sameSite = "None";
        private Expiration expire;

        public CookieBuilder() {

        }

        public CookieBuilder setName(String name) {
            if (!CookieValueValidator.isStringValid(name)) {
                throw new IllegalArgumentException(lStrings.getString("err.cookie_name_blank"));
            }
            this.name = name;

            return this;
        }

        public CookieBuilder setValue(String value) {
            if (!CookieValueValidator.isStringValid(value)) {
                throw new IllegalArgumentException("Value cannot be empty or blank.");
            }
            this.value = value;

            return this;
        }

        public CookieBuilder setMaxAge(int maxAge) {
            if (maxAge <= 0) {
                throw new IllegalArgumentException("MaxAge cannot be below 0.");
            }
            this.maxAge = maxAge;

            return this;
        }

        public CookieBuilder setDomain(String domain) {
            if (CookieValueValidator.isStringValid(domain)) {
                throw new IllegalArgumentException("Domain cannot be empty or blank.");
            }
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
            if (CookieValueValidator.isStringValid(path)) {
                throw new IllegalArgumentException("Path cannot be empty or blank.");
            }
            this.path = path;

            return this;
        }

        public CookieBuilder setVersion(int version) {
            this.version = version;

            return this;
        }

        public CookieBuilder setSameSite(String sameSite) {
            if (CookieValueValidator.isStringValid(path)) {
                throw new IllegalArgumentException("Path cannot be empty or blank.");
            }
            this.sameSite = sameSite;

            return this;
        }

        public CookieBuilder setExpire(Expiration expire) {
            this.expire = expire;

            return this;
        }

        public CookieBuilder setExpire(Date date) {
            this.expire = Expiration.date(date);

            return this;
        }

        public CookieBuilder setExpire(LocalDate localDate) {
            this.expire = Expiration.localDate(localDate);

            return this;
        }

        public CookieBuilder setExpire(LocalDateTime localDateTime) {
            this.expire = Expiration.localDateTime(localDateTime);

            return this;
        }

        public CookieValue build() {
            return new CookieValue(this);
        }
    }
}
