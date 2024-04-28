package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("pharmacy")
public class Pharmacy {
    @TableId(value = "ph_id")
    @JsonProperty("phId")
    private int phId;
    @JsonProperty("phPassword")
    private String phPassword;
    @JsonProperty("phName")
    private String phName;
    @JsonProperty("phGender")
    private String phGender;
    @JsonProperty("phEmail")
    private String phEmail;
    @JsonProperty("phPhone")
    private String phPhone;

    public Pharmacy() {
    }

    public Pharmacy(int phId, String phPassword, String phName, String phGender, String phEmail, String phPhone) {
        this.phId = phId;
        this.phPassword = phPassword;
        this.phName = phName;
        this.phGender = phGender;
        this.phEmail = phEmail;
        this.phPhone = phPhone;
    }


    public int getPhId() {
        return phId;
    }

    public void setPhId(int phId) {
        this.phId = phId;
    }

    public String getPhPassword() {
        return phPassword;
    }

    public void setPhPassword(String phPassword) {
        this.phPassword = phPassword;
    }

    public String getPhName() {
        return phName;
    }

    public void setPhName(String phName) {
        this.phName = phName;
    }

    public String getPhGender() {
        return phGender;
    }

    public void setPhGender(String phGender) {
        this.phGender = phGender;
    }

    public String getPhEmail() {
        return phEmail;
    }

    public void setPhEmail(String phEmail) {
        this.phEmail = phEmail;
    }

    public String getPhPhone() {
        return phPhone;
    }

    public void setPhPhone(String phPhone) {
        this.phPhone = phPhone;
    }


    @Override
    public String toString() {
        return "Pharmacy{" +
                "phId=" + phId +
                ", phPassword='" + phPassword + '\'' +
                ", phName='" + phName + '\'' +
                ", phGender='" + phGender + '\'' +
                ", phEmail='" + phEmail + '\'' +
                ", phPhone='" + phPhone + '\'' +
                '}';
    }
}
