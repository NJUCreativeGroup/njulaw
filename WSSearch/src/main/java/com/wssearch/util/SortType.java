package com.wssearch.util;

/**
 * Created by cristph on 2017/3/30.
 */
public enum SortType {

    DESC("desc"),
    ASC("asc");

    private String typeName;
    SortType(String typeName){
        this.typeName=typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
