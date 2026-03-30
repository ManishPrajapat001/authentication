package org.manish.authentication.dto;

public class ApiResponse<T> {
    String message;
    T data;
    boolean success;
    public ApiResponse (boolean status,String message, T data){
        this.message = message;
        this.data = data;
        this.success = status;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }




}
