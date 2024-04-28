package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("drugadmin")
public class Drugadmin {
    @TableId(value = "da_id")
    @JsonProperty("daId")
    private int daId;
    @JsonProperty("daPassword")
    private String daPassword;
    @JsonProperty("daName")
    private String daName;
    @JsonProperty("daGender")
    private String daGender;
    @JsonProperty("daEmail")
    private String daEmail;
    @JsonProperty("daPhone")
    private String daPhone;

    public Drugadmin() {
    }
    public Drugadmin(int daId, String daPassword, String daName, String daGender, String daEmail, String daPhone){
        this.daId = daId;
        this.daPassword = daPassword;
        this.daName = daName;
        this.daGender = daGender;
        this.daPhone = daPhone;
        this.daEmail = daEmail;

    }


    public int getDaId() {
        return daId;
    }

    public void setDaId(int daId) {
        this.daId = daId;
    }

    public String getDaPassword() {
        return daPassword;
    }

    public void setDaPassword(String daPassword) {
        this.daPassword = daPassword;
    }

    public String getDaName() {
        return daName;
    }

    public void setDaName(String daName) {
        this.daName = daName;
    }

    public String getDaGender() {
        return daGender;
    }

    public void setDaGender(String daGender) {
        this.daGender = daGender;
    }

    public String getDaEmail() {
        return daEmail;
    }

    public void setDaEmail(String daEmail) {
        this.daEmail = daEmail;
    }

    public String getDaPhone() {
        return daPhone;
    }

    public void setDaPhone(String daPhone) {
        this.daPhone = daPhone;
    }
    @Override
    public String toString() {
        return "Drugadmin{" +
                "daId=" + daId +
                ", daPassword='" + daPassword + '\'' +
                ", daName='" + daName + '\'' +
                ", daGender='" + daGender + '\'' +
                ", daEmail='" + daEmail + '\'' +
                ", daPhone='" + daPhone + '\'' +
                '}';
    }


}
