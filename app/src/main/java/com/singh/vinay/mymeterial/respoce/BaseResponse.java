package com.singh.vinay.mymeterial.respoce;

/**
 * Created by ios4_dev on 11/19/15.
 */
public class BaseResponse {
    private String message;

    public String getError_Code() {
        return error_Code;
    }

    public void setError_Code(String error_Code) {
        this.error_Code = error_Code;
    }

    private String error_Code;
    private boolean error;
    private String new_messages_count;

    public String getNew_messages_count() {
        return new_messages_count;
    }

    public void setNew_messages_count(String new_messages_count) {
        this.new_messages_count = new_messages_count;
    }
 public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}


