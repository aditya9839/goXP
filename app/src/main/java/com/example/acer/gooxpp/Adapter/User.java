package com.example.acer.gooxpp.Adapter;

import com.google.gson.annotations.SerializedName;


public class User {

    private String c_password;
    private String password;
    private String name;
    private String email;

    private String message;
    private boolean success;

    public DataBean data= new DataBean();

    public User(String name, String email, String password, String c_password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.c_password = c_password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getC_password() {
        return c_password;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean {

        @SerializedName("token")
        private String token;

        @SerializedName("name")
        private String name;

        public String getToken() {
            return token;
        }

        public String setToken() {
            return token;
        }

        public String getName() {
            return name;
        }

        public String setName() {
            return name;
        }
    }
}