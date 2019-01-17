package com.wssearch.elasticsearch;

import com.wssearch.util.WSInfo;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by cristph on 2017/4/27.
 */
public class Searcher {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static TransportClient transportClient;

    private final static int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
    private final static int MAX_FETCH_SIZE = 3000;
    private final static int ROLL_SIZE = 500;

    public void ini() throws UnknownHostException {
        if (transportClient == null) {
            Settings settings = Settings.builder()
                    .put("cluster.name", "WS").build();
            transportClient = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.68.72"), 9300));
            System.out.println("get transportClient");
        }
    }

    public void closeTransportClient() {
        if (transportClient != null) {
            transportClient.close();
            System.out.println("transportClient close");
        }
    }


    public List<WSInfo> getWSInfoList(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions, String beginDate, String endDate,
                                      boolean isDefault, String order, int beginIndex, int listNum) {
        try {
            ini();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        List<WSInfo> list = new ArrayList<WSInfo>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        for (String pCond : preciseConditions.keySet()) {
            boolQueryBuilder.filter(QueryBuilders.termQuery(pCond.toUpperCase(), preciseConditions.get(pCond)));
        }

        for (String aCond : ambiguousConditions.keySet()) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(aCond.toUpperCase(), ambiguousConditions.get(aCond)).analyzer("jcseg_complex"));
        }

        if (beginDate != null && beginDate.length() > 0 && endDate != null && endDate.length() > 0) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("CPRQ").gte(beginDate).lte(endDate));
        }

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style=\"color: red\">");
        highlightBuilder.postTags("</span>");
        highlightBuilder.field("QW");
        highlightBuilder.field("WS");
        highlightBuilder.field("AJJBQK");
        highlightBuilder.field("CPFXGC");
        highlightBuilder.field("PJJG");
        highlightBuilder.field("WW");
        highlightBuilder.field("AH");
        highlightBuilder.field("WSMC");
        highlightBuilder.field("FYMC");
        highlightBuilder.field("SPRY");
        highlightBuilder.field("DSR");
        highlightBuilder.field("FLYJ");

        SearchResponse scrollResp;
        if (!isDefault) {
            if (order.startsWith("a")) {
                SortBuilder sortBuilder = SortBuilders.fieldSort("CPRQ").order(SortOrder.ASC);
                scrollResp = transportClient.prepareSearch()
                        .setQuery(boolQueryBuilder)
                        .highlighter(highlightBuilder)
                        .addSort(sortBuilder)
                        .setFrom(beginIndex)
                        .setSize(listNum)
                        .execute()
                        .actionGet();
            } else {
                SortBuilder sortBuilder = SortBuilders.fieldSort("CPRQ").order(SortOrder.DESC);
                scrollResp = transportClient.prepareSearch()
                        .setQuery(boolQueryBuilder)
                        .highlighter(highlightBuilder)
                        .addSort(sortBuilder)
                        .setFrom(beginIndex)
                        .setSize(listNum)
                        .execute()
                        .actionGet();
            }
        } else {
            scrollResp = transportClient.prepareSearch()
                    .setQuery(boolQueryBuilder)
                    .highlighter(highlightBuilder)
                    .setFrom(beginIndex)
                    .setSize(listNum)
                    .execute()
                    .actionGet();
        }


        //getHits()
        for (SearchHit hit : scrollResp.getHits().getHits()) {
            //Handle the hit...
            WSInfo wsInfo = new WSInfo();
            Map<String, Object> source = hit.getSource();
            wsInfo.setAh((String) source.get("AH"));
            wsInfo.setAjlx((String) source.get("AJLX"));
            wsInfo.setFymc((String) source.get("FYMC"));
            wsInfo.setSpcx((String) source.get("SPCX"));
            wsInfo.setWslx((String) source.get("WSLX"));
            wsInfo.setXmlPath((String) source.get("XMLPATH"));
            wsInfo.setCprq((String) source.get("CPRQ"));
            wsInfo.setWsmc((String) source.get("WSMC"));
            wsInfo.setScore(hit.getScore());
            wsInfo.set_id(hit.getId());

            Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();
            String matchText = "";
            HighlightField highlightField = highlightFieldMap.get("QW");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                matchText += "<span style=\"color: black;font: larger bolder\">全文匹配情况：</span>";
                for (Text text : fragments) {
                    matchText += text;
                    matchText += "……";
                }
                matchText += "<br/><br/>";
            }

            highlightField = highlightFieldMap.get("WS");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                matchText += "<span style=\"color: black;font: larger bolder\">文首匹配情况：</span>";
                for (Text text : fragments) {
                    matchText += text;
                    matchText += "……";
                }
                matchText += "<br/><br/>";
            }

            highlightField = highlightFieldMap.get("AJJBQK");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                matchText += "<span style=\"color: black;font: larger bolder\">事实匹配情况：</span>";
                for (Text text : fragments) {
                    matchText += text;
                    matchText += "……";
                }
                matchText += "<br/><br/>";
            }

            highlightField = highlightFieldMap.get("CPFXGC");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                matchText += "<span style=\"color: black;font: larger bolder\">理由匹配情况：</span>";
                for (Text text : fragments) {
                    matchText += text;
                    matchText += "……";
                }
                matchText += "<br/><br/>";
            }

            highlightField = highlightFieldMap.get("PJJG");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                matchText += "<span style=\"color: black;font: larger bolder\">判决结果匹配情况：</span>";
                for (Text text : fragments) {
                    matchText += text;
                    matchText += "……";
                }
                matchText += "<br/><br/>";
            }

            highlightField = highlightFieldMap.get("WW");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                matchText += "<span style=\"color: black;font: larger bolder\">文尾匹配情况：</span>";
                for (Text text : fragments) {
                    matchText += text;
                    matchText += "……";
                }
                matchText += "<br/><br/>";
            }

            highlightField = highlightFieldMap.get("AH");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                matchText += "<span style=\"color: black;font: larger bolder\">案号匹配情况：</span>";
                for (Text text : fragments) {
                    matchText += text;
                    matchText += "……";
                }
                matchText += "<br/><br/>";
            }

            highlightField = highlightFieldMap.get("WSMC");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                matchText += "<span style=\"color: black;font: larger bolder\">案件名称匹配情况：</span>";
                for (Text text : fragments) {
                    matchText += text;
                    matchText += "……";
                }
                matchText += "<br/><br/>";
            }

            highlightField = highlightFieldMap.get("FYMC");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                matchText += "<span style=\"color: black;font: larger bolder\">法院名称匹配情况：</span>";
                for (Text text : fragments) {
                    matchText += text;
                    matchText += "……";
                }
                matchText += "<br/><br/>";
            }

            highlightField = highlightFieldMap.get("SPRY");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                matchText += "<span style=\"color: black;font: larger bolder\">裁判人员匹配情况：</span>";
                for (Text text : fragments) {
                    matchText += text;
                    matchText += "……";
                }
                matchText += "<br/><br/>";
            }

            highlightField = highlightFieldMap.get("DSR");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                matchText += "<span style=\"color: black;font: larger bolder\">当事人匹配情况：</span>";
                for (Text text : fragments) {
                    matchText += text;
                    matchText += "……";
                }
                matchText += "<br/><br/>";
            }

            highlightField = highlightFieldMap.get("FLYJ");
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                matchText += "<span style=\"color: black;font: larger bolder\">法律依据匹配情况：</span>";
                for (Text text : fragments) {
                    matchText += text;
                    matchText += "……";
                }
                matchText += "<br/><br/>";
            }
            wsInfo.setMatchText(matchText);
            list.add(wsInfo);
            System.out.println(wsInfo.toString());
        }
        return list;
    }

    public long getWSInfoListNum(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions,
                                 String beginDate, String endDate) {
        try {
            ini();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (String pCond : preciseConditions.keySet()) {
            boolQueryBuilder.filter(QueryBuilders.termQuery(pCond.toUpperCase(), preciseConditions.get(pCond)));
        }

        for (String aCond : ambiguousConditions.keySet()) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(aCond.toUpperCase(), ambiguousConditions.get(aCond)).analyzer("jcseg_complex"));
        }

        if (beginDate != null && beginDate.length() > 0 && endDate != null && endDate.length() > 0) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("CPRQ").gte(beginDate).lte(endDate));
        }

        SearchResponse scrollResp = transportClient.prepareSearch()
                .setScroll(new TimeValue(60000))
                .setQuery(boolQueryBuilder)
                //to only get count & not get result set back
                .setSize(0)
                .get();

        long count = scrollResp.getHits().getTotalHits();
        return count;
    }


    public HashMap<String, Integer> getGroupStatistics(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions,
                                                       String beginDate, String endDate, String groupName, String whereName, String whereValue) {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        try {
            ini();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (String pCond : preciseConditions.keySet()) {
            boolQueryBuilder.filter(QueryBuilders.termQuery(pCond.toUpperCase(), preciseConditions.get(pCond)));
        }
        for (String aCond : ambiguousConditions.keySet()) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(aCond.toUpperCase(), ambiguousConditions.get(aCond)).analyzer("jcseg_complex"));
        }

        if (beginDate != null && beginDate.length() > 0 && endDate != null && endDate.length() > 0) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("CPRQ").gte(beginDate).lte(endDate));
        }

        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("groupBy").field(groupName.toUpperCase()).size(100);

        SearchResponse scrollResp = transportClient.prepareSearch()
                .setScroll(new TimeValue(60000))
                .setQuery(boolQueryBuilder)
                .addAggregation(aggregationBuilder)
                .execute().actionGet();

        Terms agg = scrollResp.getAggregations().get("groupBy");
        List<Terms.Bucket> entries = agg.getBuckets();
        for (Terms.Bucket entry : entries) {
            String key = entry.getKeyAsString(); // bucket key
            long docCount = entry.getDocCount(); // Doc count
            hashMap.put(key, (int) docCount);
        }

        return hashMap;
    }

    private String xmlPathToDocPath(String path) {
        String[] temp = path.split("\\\\");
        temp[temp.length - 2] = "doc";
        temp[temp.length - 1] = temp[temp.length - 1].replace("xml", "doc");
        path = "";
        for (int i = 0; i < temp.length; i++) {
            path += (temp[i] + "\\");
        }
        path = path.substring(0, path.length() - 1);
        return path;
    }

    private String getSingleRealFilePath(String path, String type) {
        int pos = path.indexOf("Desktop\\");
        if (path.indexOf("刑事一审案件") > 0) {
            path = "C:\\文书\\刑事一审案件\\" + path.substring(pos + 8);
        } else {
            path = "C:\\文书\\" + path.substring(pos + 8);
        }
        if(type.equals("doc")){
            path=xmlPathToDocPath(path);
        }
        System.out.println("put file[" + path + "] into zip");
        return path;
    }

    private int zipFile(File inputFile, ZipOutputStream zipOutputStream) throws IOException, ServletException {
        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream fileInputStream = new FileInputStream(inputFile);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

                    //用文件路径名 重命名被压缩的文件
                    String tempPath = inputFile.getPath();
                    tempPath = tempPath.replace(':', '_');
                    tempPath = tempPath.replace('\\', '_');
                    tempPath = tempPath.replace('/', '_');
                    ZipEntry entry = new ZipEntry(tempPath);
                    zipOutputStream.putNextEntry(entry);


                    long streamTotal = 0; // 接受流的容量
                    int streamNum = 0; // 流需要分开的数量
                    int leaveByte = 0; // 文件剩下的字符数
                    byte[] outputBytes; // byte数组接受文件的数据

                    streamTotal = bufferedInputStream.available(); // 通过available方法取得流的最大字符数
                    streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
                    leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量

                    if (streamNum > 0) {
                        for (int j = 0; j < streamNum; ++j) {
                            outputBytes = new byte[MAX_BYTE];
                            // 读入流,保存在byte数组
                            bufferedInputStream.read(outputBytes, 0, MAX_BYTE);
                            // 写出流
                            zipOutputStream.write(outputBytes, 0, MAX_BYTE);
                        }
                    }
                    // 写出剩下的流数据
                    outputBytes = new byte[leaveByte];
                    bufferedInputStream.read(outputBytes, 0, leaveByte);
                    zipOutputStream.write(outputBytes);
                    zipOutputStream.closeEntry();

                    bufferedInputStream.close();
                    fileInputStream.close();
                    return 1;
                }
                return 0;
            } else {
                return 0;
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public File generateZip(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions, String beginDate, String endDate, String fileName, String type) {
        try {
            ini();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        for (String pCond : preciseConditions.keySet()) {
            boolQueryBuilder.filter(QueryBuilders.termQuery(pCond.toUpperCase(), preciseConditions.get(pCond)));
        }

        for (String aCond : ambiguousConditions.keySet()) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(aCond.toUpperCase(), ambiguousConditions.get(aCond)).analyzer("jcseg_complex"));
        }

        if (beginDate != null && beginDate.length() > 0 && endDate != null && endDate.length() > 0) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("CPRQ").gte(beginDate).lte(endDate));
        }

        //构造zip压缩包
        File zipFile = new File(fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(zipFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        //进行压缩存储
        zipOutputStream.setMethod(ZipOutputStream.DEFLATED);
        //压缩级别设置为1(可取值0-9，级别越高，压缩比越高，用时越长)
        zipOutputStream.setLevel(1);

        //获取查询构造器
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch()
                .setQuery(boolQueryBuilder)
                .setSize(ROLL_SIZE)
                .setScroll(new TimeValue(6000));
        //打印查询语句
        logger.info("read by scroll[roll-size=500]:", searchRequestBuilder.toString());

        int count=0;
        SearchResponse scrollResp = searchRequestBuilder.get();
        do {
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                //Handle the hit...
                String path = (String) hit.getSource().get("XMLPATH");
                count++;
                System.out.println(count+"---tmp path---" + path);
                File tmp = new File(getSingleRealFilePath(path, type));
                try {
                    zipFile(tmp, zipOutputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            }
            scrollResp = transportClient.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while (scrollResp.getHits().getHits().length != 0);

        try {
            zipOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipFile;
    }

    public File generateZip(HashMap<String, String> preciseConditions, HashMap<String, String> ambiguousConditions, String beginDate, String endDate, String fileName, String type, int beginIndex, int listNum) {
        try {
            ini();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        for (String pCond : preciseConditions.keySet()) {
            boolQueryBuilder.filter(QueryBuilders.termQuery(pCond.toUpperCase(), preciseConditions.get(pCond)));
        }

        for (String aCond : ambiguousConditions.keySet()) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(aCond.toUpperCase(), ambiguousConditions.get(aCond)).analyzer("jcseg_complex"));
        }

        if (beginDate != null && beginDate.length() > 0 && endDate != null && endDate.length() > 0) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("CPRQ").gte(beginDate).lte(endDate));
        }

        //获取查询构造器
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch()
                .setQuery(boolQueryBuilder);

        //构造zip压缩包
        File zipFile = new File(fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(zipFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        //进行压缩存储
        zipOutputStream.setMethod(ZipOutputStream.DEFLATED);
        //压缩级别设置为1(可取值0-9，级别越高，压缩比越高，用时越长)
        zipOutputStream.setLevel(1);

        int count=0;
        //如果遍历范围比较小，直接读取；否则游标读取
        if (beginIndex + listNum <= MAX_FETCH_SIZE) {
            searchRequestBuilder.setFrom(beginIndex).setSize(listNum);
            //打印查询语句
            logger.info("read total result set from cache directly:", searchRequestBuilder.toString());
            //查询
            SearchResponse scrollResp = searchRequestBuilder.execute().actionGet();
            SearchHit[] searchHits = scrollResp.getHits().getHits();
            //并行收集待下载文件路径
            List<String> paths = Arrays.stream(searchHits).parallel()
                    .map(searchHit -> (String) searchHit.getSource().get("XMLPATH"))
                    .collect(Collectors.toList());
            //下载
            paths.forEach(path -> {
                System.out.println("---tmp path---" + path);
                File tmp = new File(getSingleRealFilePath(path, type));
                try {
                    zipFile(tmp, zipOutputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            });
            try {
                zipOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return zipFile;
        } else {
            searchRequestBuilder.setSize(ROLL_SIZE).setScroll(new TimeValue(6000));
            //打印查询语句
            logger.info("read by scroll[roll-size=500]:", searchRequestBuilder.toString());
            SearchResponse scrollResp = searchRequestBuilder.get();
            int endIndex = beginIndex + listNum;
            int rowId = 0;
            int resultId = 0;
            do {
                if (rowId < beginIndex && (rowId + ROLL_SIZE) < beginIndex) {
                    //当前行序号小于目标序号
                    rowId += ROLL_SIZE;
                    scrollResp = transportClient.prepareSearchScroll(scrollResp.getScrollId())
                            .setScroll(new TimeValue(6000)).execute().actionGet();
                    continue;
                }
                //从当前序号开始，可能包含目标行
                SearchHit[] searchHits = scrollResp.getHits().getHits();
                for (int i = 0; i < searchHits.length; i++) {
                    //检查当前序号是否在目标序号内
                    if (rowId >= beginIndex && rowId < endIndex) {
                        String path = (String) searchHits[i].getSource().get("XMLPATH");
                        count++;
                        System.out.println(count+"---tmp path---" + path);
                        File tmp = new File(getSingleRealFilePath(path, type));
                        try {
                            zipFile(tmp, zipOutputStream);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ServletException e) {
                            e.printStackTrace();
                        }
                        resultId++;
                    }
                    rowId++;
                    if (resultId == listNum) {
                        try {
                            zipOutputStream.close();
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return zipFile;
                    }
                }
                scrollResp = transportClient.prepareSearchScroll(scrollResp.getScrollId())
                        .setScroll(new TimeValue(6000)).execute().actionGet();
            } while (scrollResp.getHits().getHits().length != 0);
            try {
                zipOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return zipFile;
        }
    }
}
