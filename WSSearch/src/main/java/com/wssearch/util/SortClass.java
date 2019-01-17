package com.wssearch.util;

/**
 * Created by cristph on 2017/3/30.
 */
public enum SortClass {

    FYCJ("fycj"),//法院层级
    PJRQ("pjsj"),//判决日期
    SPCX("spcx");//审判程序

    private String typeName;
    SortClass(String typeName){
        this.typeName=typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
