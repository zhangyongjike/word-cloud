package com.fexo.wordcloud.entry;

import java.io.Serializable;

public class Message<T> implements Serializable {


    /**
     *  状态码
     */
    private int statusCode;

    /**
     * 错误信息，默认为“”字符串
     */
    private String message = "";

    /**
     *  返回结果
     */
    private T result;

    public Message(int statusCode, T result) {
        this.statusCode = statusCode;
        this.result = result;
    }

    public Message(String message) {
        this.statusCode = 400;
        this.message = message;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
