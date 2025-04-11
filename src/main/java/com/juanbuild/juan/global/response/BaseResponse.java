package com.juanbuild.juan.global.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseResponse<T> {

    private T data;
    private String message;

    public BaseResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public static <T> BaseResponse<T> of (T data, String message) {
        return new BaseResponse<>(data, message);}
}
