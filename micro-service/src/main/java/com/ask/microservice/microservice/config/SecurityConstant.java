package com.ask.microservice.microservice.config;

public class SecurityConstant {
    public static final String SECRET = "ms@test.fr";
    public static final Long EXPIRATION_TIME =  (long) 860_000_000 ; // 10 days
    public static final String TOKEN_PREF_STRING = "Bearer";
    public static final String HEADER_STRING = "Authorization";
}
