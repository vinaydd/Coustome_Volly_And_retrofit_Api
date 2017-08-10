package com.singh.vinay.mymeterial.model;

import java.io.Serializable;

/**
 * Created by root on 4/7/16.
 */
public class LoginModel implements Serializable {

    private String user_id;
    private String user_email;
    private String first_name;
    private String last_name;
    private String user_image;
    private String phone_no;
    private String api_key;
    private String col_name;
    private String newsletter;
    private String notifications;
    private String new_messages_count;

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getCol_name() {
        return col_name;
    }

    public void setCol_name(String col_name) {
        this.col_name = col_name;
    }

    public String getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }

    public String getNew_messages_count() {
        return new_messages_count;
    }

    public void setNew_messages_count(String new_messages_count) {
        this.new_messages_count = new_messages_count;
    }
}
