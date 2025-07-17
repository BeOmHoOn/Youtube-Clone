package com.clone.metube.global.constants;

public class CONSTANTS {
    public static final Long ACCESS_TOKEN_EXPIRY_MILLI = 5L * 60L * 1000L; // 엑세스는 5분
    public static final Long REFRESH_TOKEN_EXPIRY_MILLI = 24L * 60L * 60L * 1000L; // 리프레시는 24시간
    public static final String REFRESH_TOKEN_PREFIX = "REFRESH_TOKEN:";
    public static final String ACCESS_TOKEN_PREFIX = "ACCESS_TOKEN:";
    public static final String BLACKLIST_ACCESS_TOKEN_PREFIX = "BLACKLIST_ACCESS_TOKEN:";
}
