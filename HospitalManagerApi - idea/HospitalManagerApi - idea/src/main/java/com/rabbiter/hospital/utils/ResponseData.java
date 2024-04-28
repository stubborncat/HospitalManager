package com.rabbiter.hospital.utils;


import java.io.Serializable;

public class ResponseData<T> implements Serializable {
    /**
     * 表示当前相应的状态是成功或者失败
     */
    private int status;
    /**
     * 表示当响应之后给前端的提示信息
     */
    private String msg;
    /**
     * 表示当响应成功之后返回给前端的数据
     */
    private T data;

    public ResponseData() {
    }

    public ResponseData(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static <T> ResponseData<T> success(String msg, T data) {
        return new ResponseData(200, msg, data);
    }

    public static ResponseData fail(String msg) {
        return new ResponseData(400, msg, null);
    }

    public static ResponseData success(String msg) {
        return new ResponseData(200, msg, null);

    }

}
