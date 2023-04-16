package com.novel.numble.global.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "MEMBER-ERR-409", "username is duplicated"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER-ERR-404", "user not founded"),
    LOGIN_ERROR(HttpStatus.NOT_FOUND, "MEMBER-ERR-404", "username or password is not match"),
    ARCHIVE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARCHIVE-ERR-404", "archive not founded"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "MEMBER-ERR-401", "Permission is invalid"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "MEMBER-ERR-401","Token is invalid"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON-ERR-500", "Internal server error"),
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
