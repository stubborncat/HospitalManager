package com.rabbiter.hospital.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("equipment")
public class Equipment {
    @TableId(value = "e_id")
    @JsonProperty("eId")
    private int eId;
    @JsonProperty("ePassword")
    private String ePassword;
    @JsonProperty("eName")
    private String eName;
    @JsonProperty("eGender")
    private String eGender;
    @JsonProperty("eEmail")
    private String eEmail;
    @JsonProperty("ePhone")
    private String ePhone;

    public Equipment() {

}

    public Equipment(int eId, String ePassword, String eName, String eGender, String eEmail, String ePhone){
        this.eId = eId;
        this.ePassword = ePassword;
        this.eName = eName;
        this.eGender = eGender;
        this.ePhone = ePhone;
        this.eEmail = eEmail;

    }


    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public String getePassword() {
        return ePassword;
    }

    public void setePassword(String ePassword) {
        this.ePassword = ePassword;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteGender() {
        return eGender;
    }

    public void seteGender(String eGender) {
        this.eGender = eGender;
    }

    public String geteEmail() {
        return eEmail;
    }

    public void seteEmail(String eEmail) {
        this.eEmail = eEmail;
    }

    public String getePhone() {
        return ePhone;
    }

    public void setePhone(String ePhone) {
        this.ePhone = ePhone;
    }


    @Override
    public String toString() {
        return "Equipment{" +
                "eId=" + eId +
                ", ePassword='" + ePassword + '\'' +
                ", eName='" + eName + '\'' +
                ", eGender='" + eGender + '\'' +
                ", eEmail='" + eEmail + '\'' +
                ", ePhone='" + ePhone + '\'' +
                '}';
    }
}




