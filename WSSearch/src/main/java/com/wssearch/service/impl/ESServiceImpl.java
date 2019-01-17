package com.wssearch.service.impl;

import com.wssearch.elasticsearch.Searcher;
import com.wssearch.service.ESService;
import com.wssearch.util.WSInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cristph on 2017/4/27.
 */

@Transactional
@Service("esService")
public class ESServiceImpl implements ESService {

    private static Searcher searcher;

    public ESServiceImpl(){
        if(searcher==null){
            searcher=new Searcher();
        }
    }

    public Searcher getSearcher(){
        if(searcher==null){
            searcher=new Searcher();
        }
        return searcher;
    }

    @Override
    public List<WSInfo> getWSInfoList(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions, String beginDate, String endDate, boolean isDefault, String order, int beginIndex, int listNum) {
        return getSearcher().getWSInfoList(preciseConditions, ambiguousConditions, beginDate, endDate, isDefault, order, beginIndex, listNum);
    }

    @Override
    public long getWSInfoListNum(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions, String beginDate, String endDate) {
        return getSearcher().getWSInfoListNum(preciseConditions, ambiguousConditions, beginDate, endDate);
    }

    @Override
    public HashMap<String, Integer> getGroupStatistics(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions, String beginDate, String endDate, String groupName, String whereName, String whereValue) {
        return getSearcher().getGroupStatistics(preciseConditions, ambiguousConditions, beginDate, endDate, groupName, whereName, whereValue);
    }

    @Override
    public File generateZip(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions, String beginDate, String endDate, String fileName, String type) {
        return getSearcher().generateZip(preciseConditions, ambiguousConditions, beginDate, endDate, fileName, type);
    }

    @Override
    public File generateZip(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions, String beginDate, String endDate, String fileName, String type, int beginIndex, int endIndex) {
        return getSearcher().generateZip(preciseConditions, ambiguousConditions, beginDate, endDate, fileName, type, beginIndex, endIndex);
    }
}
