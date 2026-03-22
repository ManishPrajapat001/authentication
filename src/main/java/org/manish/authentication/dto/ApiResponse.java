package org.manish.authentication.dto;

import org.manish.authentication.entity.User;

public class ApiResponse {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    String message;
    Object user;
    boolean success;
    public ApiResponse (boolean status,String message, User user){
        this.message = message;
        this.user = user;
        this.success = status;
    }


}
