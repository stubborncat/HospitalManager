package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("emergency")

public class Emergency {

    @TableId(value = "ed_id")
    @JsonProperty("edId")
    private Integer edId;

    @JsonProperty("dId")
    @TableField(value = "d_id")
    private int dId;

    @JsonProperty("dCard")
    @TableField(value = "d_card")
    private String dCard;

    @JsonProperty("dName")
    @TableField(value = "d_name")
    private String dName;

    @JsonProperty("dRecord")
    @TableField(value = "d_record")
    private String dRecord;

    @JsonProperty("dDrug")
    @TableField(value = "d_drug")
    private String dDrug;

    @JsonProperty("dTotalPrice")
    @TableField(value = "d_total_price")
    private Double dTotalPrice;

    @JsonProperty("dState")
    @TableField(value = "d_state")
    private Integer dState;

    @JsonProperty("dEnd")
    @TableField(value = "d_end")
    private String dEnd;


    @JsonProperty("dNight")
    @TableField(value = "d_night")
    private int dNight;

    @JsonProperty("dCheck")
    @TableField(value = "d_Check")
    private String dCheck;

    @JsonProperty("cTotalPrice")
    @TableField(value = "c_total_price")
    private Double cTotalPrice;

    @JsonProperty("dSum")
    @TableField(value = "d_sum")
    private Double dSum;

public Emergency(){
}


    public Emergency(int edId, int dId , String dCard , String dName ,String dDrug, String dRecord , double dTotalPrice, Integer dState , String dEnd, int dNight , String dCheck , Double cTotalPrice, double dSum){
        this.edId = edId;
        this.dId = dId;
        this.dCard = dCard;
        this.dName = dName;
        this.dRecord = dRecord;
        this.dDrug = dDrug;
        this.dTotalPrice = dTotalPrice;
        this.dState = dState;
        this.dEnd = dEnd;
        this.dNight = dNight;
        this.dCheck = dCheck;
        this.cTotalPrice = cTotalPrice;
        this.dSum = dSum;
    }

    public Integer getEdId() {
        return edId;
    }

    public void setEdId(Integer edId) {
        this.edId = edId;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getdCard() {
        return dCard;
    }

    public void setdCard(String dCard) {
        this.dCard = dCard;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdRecord() {
        return dRecord;
    }

    public void setdRecord(String dRecord) {
        this.dRecord = dRecord;
    }

    public String getdDrug() {
        return dDrug;
    }

    public void setdDrug(String dDrug) {
        this.dDrug = dDrug;
    }

    public Double getdTotalPrice() {
        return dTotalPrice;
    }

    public void setdTotalPrice(Double dTotalPrice) {
        this.dTotalPrice = dTotalPrice;
    }

    public Integer getdState() {
        return dState;
    }

    public void setdState(Integer dState) {
        this.dState = dState;
    }

    public String getdEnd() {
        return dEnd;
    }

    public void setDend(String dEnd) {
        this.dEnd = dEnd;
    }

    public int getdNight() {
        return dNight;
    }

    public void setdNight(int dNight) {
        this.dNight = dNight;
    }

    public String getdCheck() {
        return dCheck;
    }

    public void setdCheck(String dCheck) {
        this.dCheck = dCheck;
    }

    public Double getcTotalPrice() {
        return cTotalPrice;
    }

    public void setcTotalPrice(Double cTotalPrice) {
        this.cTotalPrice = cTotalPrice;
    }

    public Double getdSum() {
        return dSum;
    }

    public void setdSum(Double dSum) {
        this.dSum = dSum;
    }

    @Override
    public String toString() {
        return "Emergency{" +
                "edId=" + edId +
                ", dId=" + dId +
                ", dCard='" + dCard + '\'' +
                ", dName='" + dName + '\'' +
                ", dRecord='" + dRecord + '\'' +
                ", dDrug='" + dDrug + '\'' +
                ", dTotalPrice=" + dTotalPrice +
                ", dState=" + dState +
                ", dEnd='" + dEnd + '\'' +
                ", dNight=" + dNight +
                ", dCheck='" + dCheck + '\'' +
                ", cTotalPrice=" + cTotalPrice +
                ", dSum=" + dSum +
                '}';
    }
}
