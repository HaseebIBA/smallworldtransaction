package com.smallworld.Miscellaneous;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class ErrorCode {

    public static final String OK = "0000";
    public static final String BAD_REQUEST = "0001";
    public static final String NOT_FOUND = "0002";
    public static final String INTERNAL_SERVER_ERROR = "0003";
    public static final String UNAUTHORIZED = "0004";
    public static final String FORBIDDEN = "0005";
    public static final String METHOD_NOT_ALLOWED = "0006";
    public static final String CONFLICT = "0007";
    public static final String UNSUPPORTED_MEDIA_TYPE = "0008";


    private static final Map<String, String> codeToDescriptionMap = new HashMap<>();

    static {
        codeToDescriptionMap.put(OK, "OK");
        codeToDescriptionMap.put(BAD_REQUEST, "Bad Request");
        codeToDescriptionMap.put(NOT_FOUND, "Not Found");
        codeToDescriptionMap.put(INTERNAL_SERVER_ERROR, "Internal Server Error");
        codeToDescriptionMap.put(UNAUTHORIZED, "Unauthorized");
        codeToDescriptionMap.put(FORBIDDEN, "Forbidden");
        codeToDescriptionMap.put(METHOD_NOT_ALLOWED, "Method Not Allowed");
        codeToDescriptionMap.put(CONFLICT, "Conflict");
        codeToDescriptionMap.put(UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type");
    }

    public static String getDescription(String code) {
        return codeToDescriptionMap.getOrDefault(code, "Unknown");
    }
}
