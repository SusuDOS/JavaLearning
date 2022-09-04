package com.itheima.exception;

//自定义异常处理器，用于封装异常信息，对异常进行分类
public class SystemException extends RuntimeException {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SystemException(int code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
