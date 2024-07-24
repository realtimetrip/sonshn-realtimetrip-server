package com.venti.realtimetrip.global.exception;

import com.venti.realtimetrip.global.status.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private BaseResponseStatus status;
    private String message;
    private int code;

    public BaseException(BaseResponseStatus status){
        this.status = status;
    }

    public BaseException(String message, int code){
        this.message = message;
        this.code = code;
    }

}
