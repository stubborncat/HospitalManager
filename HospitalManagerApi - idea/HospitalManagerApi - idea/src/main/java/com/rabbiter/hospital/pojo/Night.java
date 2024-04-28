package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("Night")

public class Night {

    @TableId(value = "n_id")
    @JsonProperty("nId")
    private int nId;
    @JsonProperty("nPassword")
    private String nPassword;
    @JsonProperty("nName")
    private String nName;
    @JsonProperty("nGender")
    private String nGender;
    @JsonProperty("nEmail")
    private String nEmail;
    @JsonProperty("nPhone")
    private String nPhone;



    public Night() {
    }
    public Night(int nId, String nPassword, String nName, String nGender, String nEmail, String nPhone){
        this.nId = nId;
        this.nPassword = nPassword;
        this.nName = nName;
        this.nGender = nGender;
        this.nPhone = nPhone;
        this.nEmail = nEmail;

    }

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    public String getnPassword() {
        return nPassword;
    }

    public void setnPassword(String nPassword) {
        this.nPassword = nPassword;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public String getnGender() {
        return nGender;
    }

    public void setnGender(String nGender) {
        this.nGender = nGender;
    }

    public String getnEmail() {
        return nEmail;
    }

    public void setnEmail(String nEmail) {
        this.nEmail = nEmail;
    }

    public String getnPhone() {
        return nPhone;
    }

    public void setnPhone(String nPhone) {
        this.nPhone = nPhone;
    }

    @Override
    public String toString() {
        return "Night{" +
                "nId=" + nId +
                ", nPassword='" + nPassword + '\'' +
                ", nName='" + nName + '\'' +
                ", nGender='" + nGender + '\'' +
                ", nEmail='" + nEmail + '\'' +
                ", nPhone='" + nPhone + '\'' +
                '}';
    }
}
