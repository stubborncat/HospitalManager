package com.rabbiter.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;


@TableName("buy")
public class Buy {
    @TableId(value = "db_id")
    @JsonProperty("dbId")
    private Integer dbId;

    @JsonProperty("pId")
    @TableField(value = "p_id")
    private int pId;

    @JsonProperty("pName")
    @TableField(value = "p_name")
    private String pName;

    @JsonProperty("dbDrug")
    @TableField(value = "db_drug")
    private String dbDrug;

    @JsonProperty("dbTotalPrice")
    @TableField(value = "db_total_price")
    private Double dbTotalPrice;

    @JsonProperty("dbState")
    @TableField(value = "db_state")
    private Integer dbState;

    @JsonProperty("dbend")
    @TableField(value = "db_end")
    private String dbend;


    @JsonProperty("dbnight")
    @TableField(value = "db_night")
    private int dbnight;

public Buy() {
}

    public Buy(int dbId , int pId , String pName , String dbDrug , Double dbTotalPrice , Integer dbState , String dbend , int dbnight){
        this.dbId = dbId;
        this.pId = pId;
        this.pName = pName;
        this.dbDrug = dbDrug;
        this.dbTotalPrice = dbTotalPrice;
        this.dbState = dbState;
        this.dbend = dbend;
        this.dbnight = dbnight;
        }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getDbDrug() {
        return dbDrug;
    }

    public void setDbDrug(String dbDrug) {
        this.dbDrug = dbDrug;
    }

    public Double getDbTotalPrice() {
        return dbTotalPrice;
    }

    public void setDbTotalPrice(Double dbTotalPrice) {
        this.dbTotalPrice = dbTotalPrice;
    }

    public Integer getDbState() {
        return dbState;
    }

    public  void setDbState(Integer dbState) {
        this.dbState = dbState;
    }

    public String getDbend() {
        return dbend;
    }

    public  void setDbend(String dbend) {
        this.dbend = dbend;
    }

    public int getDbnight() {
        return dbnight;
    }

    public void setDbnight(int dbnight) {
        this.dbnight = dbnight;
    }

    @Override
    public String toString() {
        return "Buy{" +
                "dbId=" + dbId +
                ", pId=" + pId +
                ", pName='" + pName + '\'' +
                ", dbDrug='" + dbDrug + '\'' +
                ", dbTotalPrice=" + dbTotalPrice +
                ", dbState=" + dbState +
                ", dbend=" + dbend +
                ", dbnight=" + dbnight +
                '}';
    }
}
