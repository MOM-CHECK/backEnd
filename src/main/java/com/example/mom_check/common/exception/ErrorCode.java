package com.example.mom_check.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // BAD_REQUEST
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Bad Request", "잘못된 요청입니다."),
    // 잘못된 값 입력 오류
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "Bad Request", "올바르지 않은 입력값입니다."),
    // 잘못된 비밀번호 입력 오류
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST.value(), "Bad Request", "비밀번호가 일치하지 않습니다."),
    // 파일을 업로드 할 수 없을 때 오류
    FILE_UPLOAD_FAILED(HttpStatus.BAD_REQUEST.value(), "Bad Request", "파일을 업로드 하는 데 실패했습니다."),
    // 아기 성별이 잘못됨
    WRONG_SEX_TYPE(HttpStatus.BAD_REQUEST.value(), "Bad Request", "성별은 GIRL과 BOY와 X만 가능합니다."),

    // 만료된 토큰 오류
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", "유효하지 않은 토큰입니다."),
    // 잘못된 값의 토큰 오류
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", "잘못된 값의 토큰입니다."),
    // 권한 없는 회원 오류
    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", "권한이 없습니다."),

    // 찾을 수 없는 회원 오류
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Not Found", "존재하지 않는 회원입니다."),
    // 이미지를 찾을 수 없는 오류
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Not Found", "존재하지 않는 이미지입니다."),
    // 찾을 수 없는 아기 오류
    BABY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Not Found", "존재하지 않는 아기입니다."),
    // 찾을 수 없는 임신 전 신체 정보
    INITIAL_PHYSICAL_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Not Found", "존재하지 않는 임신 전 신체 정보입니다."),

    // 이메일 중복 오류
    DUPLICATED_EMAIL(HttpStatus.CONFLICT.value(), "Conflict", "이미 존재하는 이메일입니다."),

    // 접근이 금지된 오류
    ACCESS_DENIED(HttpStatus.FORBIDDEN.value(), "Forbidden", "접근이 거부됐습니다."),
    // 잘못된 HTTP 메서드 호출 오류
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method Not Allowed", "잘못된 HTTP 메서드를 호출했습니다."),
    // 서버 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "서버 에러가 발생했습니다.");

    private final int statusCode;
    private final String reason;
    private final String message;
}