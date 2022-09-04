package com.itheima.controller;


//创建Result，统一数据返回结果类

//对于结果封装，我们应该是在表现层进行处理，所以我们把结果类放在controller包下，当然也可以放在domain包
public class Result {
    private Object data;
    private Integer code;
    private String msg;

    public Result() {
    }

    public Result(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code, Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
