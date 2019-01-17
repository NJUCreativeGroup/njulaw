package com.wssearch.service;

import com.wssearch.util.WSInfo;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cristph on 2017/4/27.
 */


public interface ESService {

    List<WSInfo>      getWSInfoList(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions, String beginDate, String endDate,
                               boolean isDefault, String order, int beginIndex, int listNum);


    long getWSInfoListNum(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions,
                          String beginDate, String endDate);


    HashMap<String, Integer> getGroupStatistics(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions,
                                                String beginDate, String endDate, String groupName, String whereName, String whereValue);


    File generateZip(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions, String beginDate, String endDate, String fileName, String type);

    File generateZip(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions, String beginDate, String endDate, String fileName, String type, int beginIndex, int listNum);

}
