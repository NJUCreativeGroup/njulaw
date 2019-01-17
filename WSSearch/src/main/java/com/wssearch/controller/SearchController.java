package com.wssearch.controller;

import com.alibaba.fastjson.JSON;
import com.wssearch.service.*;
import com.wssearch.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cristph on 2017/3/24.
 */

@Controller
public class SearchController {

    @Resource
    ESService esService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/getDocContent")
    @ResponseBody
    public String getDocContent(@RequestParam("id")String id,HttpSession session){
        List<WSInfo> wsInfoList = (List<WSInfo>) session.getAttribute("list");
        for (WSInfo info : wsInfoList) {
            if (info.getAh().equals(id)){
                return info.toString();
            }
        }
        return "sorry you don't in the right position~~~~~!";
    }

    @RequestMapping("/searchDoc")
    @ResponseBody
    public String simpleSearch(@RequestParam("kw")String kw, Model model, HttpSession session){
        HashMap<String, String> preciseConditions = new HashMap<>();
        HashMap<String, String> ambiguousConditions = new HashMap<>();
        if (kw.trim().length() != 0) {
            String qwjsInputUtf8 = null;
            try {
                qwjsInputUtf8 = URLDecoder.decode(kw, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ambiguousConditions.put(kw, qwjsInputUtf8.trim());
        }
        String cprqbeginUtf8 = null;
        String cprqendUtf8 = null;
        String cprqbegin = "1990-01-01";
        String cprqend = "2999-01-01";
        try {
            cprqbeginUtf8 = URLDecoder.decode(cprqbegin, "utf-8").trim();
            cprqendUtf8 = URLDecoder.decode(cprqend, "utf-8").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /**
         * 这里是个示例代码，后期要记得修改
         */
//        List<WSInfo> list = esService.getWSInfoList(preciseConditions, ambiguousConditions, cprqbeginUtf8.trim(), cprqendUtf8.trim(), true, "", 0, 5);
        List<WSInfo> list = new ArrayList<>();
        WSInfo info = new WSInfo();
        info.setAh("hello this is the first ws info");
        list.add(info);

//        session.setAttribute("list",list);
        List<WSInfo> wsInfoList = list;
//        List<WSInfoBrief> wsInfoBriefs = list.stream().map(wsInfo -> new WSInfoBrief(wsInfo.getAh(),wsInfo.getAjlx(),wsInfo.getSpcx())).collect(Collectors.toList());
        return wsInfoList.toString();
    }

    @RequestMapping(value = "/complexSearch")
    public String complexSearch(@RequestParam("qwjs") String qwjs,
                                @RequestParam("qwjsInput") String qwjsInput,
                                @RequestParam("ay") String ay,
                                @RequestParam("ah") String ah,
                                @RequestParam("ajmc") String ajmc,
                                @RequestParam("fymc") String fymc,
                                @RequestParam("fycj") String fycj,
                                @RequestParam("ajlx") String ajlx,
                                @RequestParam("spcx") String spcx,
                                @RequestParam("wslx") String wslx,
                                @RequestParam("cprqbegin") String cprqbegin,
                                @RequestParam("cprqend") String cprqend,
                                @RequestParam("cpry") String cpry,
                                @RequestParam("dsr") String dsr,
                                @RequestParam("flyj") String flyj,
                                @RequestParam("cpnf") String cpnf,
                                Model model) {
        HashMap<String, String> preciseConditions = new HashMap<>();
        HashMap<String, String> ambiguousConditions = new HashMap<>();

        String cprqbeginUtf8 = null;
        String cprqendUtf8 = null;

        try {
            if (qwjsInput.trim().length() != 0) {
                String qwjsInputUtf8 = URLDecoder.decode(qwjsInput, "utf-8");
                ambiguousConditions.put(qwjs, qwjsInputUtf8.trim());
            }
            if (ah.trim().length() != 0) {
                String ahUtf8 = URLDecoder.decode(ah, "utf-8");
                ambiguousConditions.put("ah", ahUtf8.trim());
            }
            if (ajmc.trim().length() != 0) {
                String ajmcUtf8 = URLDecoder.decode(ajmc, "utf-8");
                ambiguousConditions.put("wsmc", ajmcUtf8.trim());
            }
            if (fycj.trim().length() > 0) {
                String fycjUtf8 = URLDecoder.decode(fycj, "utf-8").trim();
                preciseConditions.put("fycj", fycjUtf8.trim());
            }
            if (ajlx.trim().length() != 0) {
                String ajlxUtf8 = URLDecoder.decode(ajlx, "utf-8");
                preciseConditions.put("ajlx", ajlxUtf8.trim());
            }
            if (spcx.trim().length() != 0) {
                String spcxUtf8 = URLDecoder.decode(spcx, "utf-8");
                preciseConditions.put("spcx", spcxUtf8.trim());
            }
            if (wslx.trim().length() != 0) {
                String wslxUtf8 = URLDecoder.decode(wslx, "utf-8");
                preciseConditions.put("wslx", wslxUtf8.trim());
            }
            if (cpry.trim().length() != 0) {
                String cpryUtf8 = URLDecoder.decode(cpry, "utf-8");
                ambiguousConditions.put("spry", cpryUtf8.trim());
            }
            if (flyj.trim().length() != 0) {
                String flyjUtf8 = URLDecoder.decode(flyj, "utf-8");
                ambiguousConditions.put("flyj", flyjUtf8.trim());
            }
            if (cpnf.trim().length() != 0) {
                String cpnfUtf8 = URLDecoder.decode(cpnf, "utf-8");
                preciseConditions.put("cpnf", cpnfUtf8.trim());
            }
            if (ay.trim().length() != 0) {
                String ayUtf8 = URLDecoder.decode(ay, "utf-8").trim();
                preciseConditions.put("ay", ayUtf8);
            }
            if (dsr.trim().length() != 0) {
                String dsrUtf8 = URLDecoder.decode(dsr, "utf-8").trim();
                ambiguousConditions.put("dsr", dsrUtf8);
            }
            if (fymc.trim().length() != 0) {
                String fymcUtf8 = URLDecoder.decode(fymc, "utf-8").trim();
                ambiguousConditions.put("fymc", fymcUtf8);
            }
            cprqbeginUtf8 = URLDecoder.decode(cprqbegin, "utf-8").trim();
            cprqendUtf8 = URLDecoder.decode(cprqend, "utf-8").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        List<WSInfo> list = esService.getWSInfoList(preciseConditions, ambiguousConditions, cprqbeginUtf8.trim(), cprqendUtf8.trim(), true, "", 0, 5);

        model.addAttribute("list", list);
        model.addAttribute("ah", ah);
        model.addAttribute("ay", ay);
        model.addAttribute("fycj", fycj);
        model.addAttribute("fymc", fymc);
        model.addAttribute("ajmc", ajmc);
        model.addAttribute("ajlx", ajlx);
        model.addAttribute("spcx", spcx);
        model.addAttribute("wslx", wslx);
        model.addAttribute("cpry", cpry);
        model.addAttribute("dsr", dsr);
        model.addAttribute("flyj", flyj);
        model.addAttribute("cpnf", cpnf);
        model.addAttribute("qwjs", qwjs);
        model.addAttribute("qwjsInput", qwjsInput);

        model.addAttribute("SortClass", SortClass.PJRQ);
        model.addAttribute("SortType", SortType.DESC);

        int count = 0;

        count = (int) esService.getWSInfoListNum(preciseConditions, ambiguousConditions, cprqbeginUtf8.trim(), cprqendUtf8.trim());
        model.addAttribute("AjCount", count);
        model.addAttribute("InputCount", 10);

        int maxPageNum = 0;
        //修改
        if (count % 5 == 0) {
            maxPageNum = count / 5;
        } else {
            maxPageNum = (count / 5) + 1;
        }
        model.addAttribute("maxPageNum", maxPageNum);
        return "search";
    }

    @ResponseBody
    @RequestMapping(value = "/getNum")
    public String getNum(@RequestParam("qwjs") String qwjs,
                         @RequestParam("qwjsInput") String qwjsInput,
                         @RequestParam("ay") String ay,
                         @RequestParam("ah") String ah,
                         @RequestParam("ajmc") String ajmc,
                         @RequestParam("fymc") String fymc,
                         @RequestParam("fycj") String fycj,
                         @RequestParam("ajlx") String ajlx,
                         @RequestParam("spcx") String spcx,
                         @RequestParam("wslx") String wslx,
                         @RequestParam("cprqbegin") String cprqbegin,
                         @RequestParam("cprqend") String cprqend,
                         @RequestParam("cpry") String cpry,
                         @RequestParam("dsr") String dsr,
                         @RequestParam("flyj") String flyj,
                         @RequestParam("cpnf") String cpnf) {
        HashMap<String, String> preciseConditions = new HashMap<>();
        HashMap<String, String> ambiguousConditions = new HashMap<>();

        String cprqbeginUtf8 = null;
        String cprqendUtf8 = null;

        try {
            if (qwjsInput.trim().length() != 0) {
                String qwjsInputUtf8 = URLDecoder.decode(qwjsInput, "utf-8");
                ambiguousConditions.put(qwjs, qwjsInputUtf8.trim());
            }
            if (ah.trim().length() != 0) {
                String ahUtf8 = URLDecoder.decode(ah, "utf-8");
                ambiguousConditions.put("ah", ahUtf8.trim());
            }
            if (ajmc.trim().length() != 0) {
                String ajmcUtf8 = URLDecoder.decode(ajmc, "utf-8");
                ambiguousConditions.put("wsmc", ajmcUtf8.trim());
            }
            if (fycj.trim().length() > 0) {
                String fycjUtf8 = URLDecoder.decode(fycj, "utf-8").trim();
                preciseConditions.put("fycj", fycjUtf8.trim());
            }
            if (ajlx.trim().length() != 0) {
                String ajlxUtf8 = URLDecoder.decode(ajlx, "utf-8");
                preciseConditions.put("ajlx", ajlxUtf8.trim());
            }
            if (spcx.trim().length() != 0) {
                String spcxUtf8 = URLDecoder.decode(spcx, "utf-8");
                preciseConditions.put("spcx", spcxUtf8.trim());
            }
            if (wslx.trim().length() != 0) {
                String wslxUtf8 = URLDecoder.decode(wslx, "utf-8");
                preciseConditions.put("wslx", wslxUtf8.trim());
            }
            if (cpry.trim().length() != 0) {
                String cpryUtf8 = URLDecoder.decode(cpry, "utf-8");
                ambiguousConditions.put("spry", cpryUtf8.trim());
            }
            if (flyj.trim().length() != 0) {
                String flyjUtf8 = URLDecoder.decode(flyj, "utf-8");
                ambiguousConditions.put("flyj", flyjUtf8.trim());
            }
            if (cpnf.trim().length() != 0) {
                String cpnfUtf8 = URLDecoder.decode(cpnf, "utf-8");
                preciseConditions.put("cpnf", cpnfUtf8.trim());
            }
            if (ay.trim().length() != 0) {
                String ayUtf8 = URLDecoder.decode(ay, "utf-8").trim();
                preciseConditions.put("ay", ayUtf8);
            }
            if (dsr.trim().length() != 0) {
                String dsrUtf8 = URLDecoder.decode(dsr, "utf-8").trim();
                ambiguousConditions.put("dsr", dsrUtf8);
            }
            if (fymc.trim().length() != 0) {
                String fymcUtf8 = URLDecoder.decode(fymc, "utf-8").trim();
                ambiguousConditions.put("fymc", fymcUtf8);
            }
            cprqbeginUtf8 = URLDecoder.decode(cprqbegin, "utf-8").trim();
            cprqendUtf8 = URLDecoder.decode(cprqend, "utf-8").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int count = 0;
        count = (int) esService.getWSInfoListNum(preciseConditions, ambiguousConditions, cprqbeginUtf8.trim(), cprqendUtf8.trim());
        int maxPageNum = 0;
        if (count % 5 == 0) {
            maxPageNum = count / 5;
        } else {
            maxPageNum = (count / 5) + 1;
        }

        String str = String.valueOf(count) + ";" + String.valueOf(maxPageNum);
        System.out.println(str + "SearchController-method[getNum] return[String]:{" + str + "}");
        return str;
    }

    @ResponseBody
    @RequestMapping(value = "/goPage", produces = "text/html;charset=cp936")
    public ModelAndView goPage(@RequestParam("qwjs") String qwjs,
                               @RequestParam("qwjsInput") String qwjsInput,
                               @RequestParam("ay") String ay,
                               @RequestParam("ah") String ah,
                               @RequestParam("ajmc") String ajmc,
                               @RequestParam("fymc") String fymc,
                               @RequestParam("fycj") String fycj,
                               @RequestParam("ajlx") String ajlx,
                               @RequestParam("spcx") String spcx,
                               @RequestParam("wslx") String wslx,
                               @RequestParam("cprqbegin") String cprqbegin,
                               @RequestParam("cprqend") String cprqend,
                               @RequestParam("cpry") String cpry,
                               @RequestParam("dsr") String dsr,
                               @RequestParam("flyj") String flyj,
                               @RequestParam("cpnf") String cpnf,
                               @RequestParam("SortClass[]") String[] sortClass,
                               @RequestParam("SortType[]") String[] sortType,
                               @RequestParam("BeginIndex") int BeginIndex,
                               ModelAndView modelAndView) {

        HashMap<String, String> preciseConditions = new HashMap<>();
        HashMap<String, String> ambiguousConditions = new HashMap<>();

        String cprqbeginUtf8 = null;
        String cprqendUtf8 = null;

        try {
            if (qwjsInput.trim().length() != 0) {
                String qwjsInputUtf8 = URLDecoder.decode(qwjsInput, "utf-8");
                ambiguousConditions.put(qwjs, qwjsInputUtf8.trim());
            }
            if (ah.trim().length() != 0) {
                String ahUtf8 = URLDecoder.decode(ah, "utf-8");
                ambiguousConditions.put("ah", ahUtf8.trim());
            }
            if (ajmc.trim().length() != 0) {
                String ajmcUtf8 = URLDecoder.decode(ajmc, "utf-8");
                ambiguousConditions.put("wsmc", ajmcUtf8.trim());
            }
            if (fycj.trim().length() > 0) {
                String fycjUtf8 = URLDecoder.decode(fycj, "utf-8").trim();
                preciseConditions.put("fycj", fycjUtf8.trim());
            }
            if (ajlx.trim().length() != 0) {
                String ajlxUtf8 = URLDecoder.decode(ajlx, "utf-8");
                preciseConditions.put("ajlx", ajlxUtf8.trim());
            }
            if (spcx.trim().length() != 0) {
                String spcxUtf8 = URLDecoder.decode(spcx, "utf-8");
                preciseConditions.put("spcx", spcxUtf8.trim());
            }
            if (wslx.trim().length() != 0) {
                String wslxUtf8 = URLDecoder.decode(wslx, "utf-8");
                preciseConditions.put("wslx", wslxUtf8.trim());
            }
            if (cpry.trim().length() != 0) {
                String cpryUtf8 = URLDecoder.decode(cpry, "utf-8");
                ambiguousConditions.put("spry", cpryUtf8.trim());
            }
            if (flyj.trim().length() != 0) {
                String flyjUtf8 = URLDecoder.decode(flyj, "utf-8");
                ambiguousConditions.put("flyj", flyjUtf8.trim());
            }
            if (cpnf.trim().length() != 0) {
                String cpnfUtf8 = URLDecoder.decode(cpnf, "utf-8");
                preciseConditions.put("cpnf", cpnfUtf8.trim());
            }
            if (ay.trim().length() != 0) {
                String ayUtf8 = URLDecoder.decode(ay, "utf-8").trim();
                preciseConditions.put("ay", ayUtf8);
            }
            if (dsr.trim().length() != 0) {
                String dsrUtf8 = URLDecoder.decode(dsr, "utf-8").trim();
                ambiguousConditions.put("dsr", dsrUtf8);
            }
            if (fymc.trim().length() != 0) {
                String fymcUtf8 = URLDecoder.decode(fymc, "utf-8").trim();
                ambiguousConditions.put("fymc", fymcUtf8);
            }
            cprqbeginUtf8 = URLDecoder.decode(cprqbegin, "utf-8").trim();
            cprqendUtf8 = URLDecoder.decode(cprqend, "utf-8").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<WSInfo> list = null;
        if (sortClass[0].startsWith("a")) {
            System.out.println("SearchController-method[goPage]------order by " + sortClass[0] + " " + sortType[0] + " from " + (BeginIndex - 1) * 5 + " get 5");
            list = esService.getWSInfoList(preciseConditions, ambiguousConditions, cprqbeginUtf8.trim(), cprqendUtf8.trim(), true, "", (BeginIndex - 1) * 5, 5);
        } else {
            System.out.println("SearchController-method[goPage]------order by " + sortClass[0] + " " + sortType[0] + " from " + (BeginIndex - 1) * 5 + " get 5");
            list = esService.getWSInfoList(preciseConditions, ambiguousConditions, cprqbeginUtf8.trim(), cprqendUtf8.trim(), false, sortType[0], (BeginIndex - 1) * 5, 5);
        }
        modelAndView.addObject("list", list);
        modelAndView.setViewName("ajPage");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/groupStatistics", produces = "application/json;charset=cp936")
    public String getGroupStatistics(@RequestParam("qwjs") String qwjs,
                                     @RequestParam("qwjsInput") String qwjsInput,
                                     @RequestParam("ay") String ay,
                                     @RequestParam("ah") String ah,
                                     @RequestParam("ajmc") String ajmc,
                                     @RequestParam("fymc") String fymc,
                                     @RequestParam("fycj") String fycj,
                                     @RequestParam("ajlx") String ajlx,
                                     @RequestParam("spcx") String spcx,
                                     @RequestParam("wslx") String wslx,
                                     @RequestParam("cprqbegin") String cprqbegin,
                                     @RequestParam("cprqend") String cprqend,
                                     @RequestParam("cpry") String cpry,
                                     @RequestParam("dsr") String dsr,
                                     @RequestParam("flyj") String flyj,
                                     @RequestParam("cpnf") String cpnf,
                                     @RequestParam("groupName") String groupName,
                                     @RequestParam("whereName") String whereName,
                                     @RequestParam("whereValue") String whereValue) {
        HashMap<String, Integer> hashMap = null;
        HashMap<String, String> preciseConditions = new HashMap<>();
        HashMap<String, String> ambiguousConditions = new HashMap<>();
        String cprqbeginUtf8 = null;
        String cprqendUtf8 = null;
        try {
            if (qwjsInput.trim().length() != 0) {
                String qwjsInputUtf8 = URLDecoder.decode(qwjsInput, "utf-8");
                ambiguousConditions.put(qwjs, qwjsInputUtf8.trim());
            }
            if (ah.trim().length() != 0) {
                String ahUtf8 = URLDecoder.decode(ah, "utf-8");
                ambiguousConditions.put("ah", ahUtf8.trim());
            }
            if (ajmc.trim().length() != 0) {
                String ajmcUtf8 = URLDecoder.decode(ajmc, "utf-8");
                ambiguousConditions.put("wsmc", ajmcUtf8.trim());
            }
            if (fycj.trim().length() > 0) {
                String fycjUtf8 = URLDecoder.decode(fycj, "utf-8").trim();
                preciseConditions.put("fycj", fycjUtf8.trim());
            }
            if (ajlx.trim().length() != 0) {
                String ajlxUtf8 = URLDecoder.decode(ajlx, "utf-8");
                preciseConditions.put("ajlx", ajlxUtf8.trim());
            }
            if (spcx.trim().length() != 0) {
                String spcxUtf8 = URLDecoder.decode(spcx, "utf-8");
                preciseConditions.put("spcx", spcxUtf8.trim());
            }
            if (wslx.trim().length() != 0) {
                String wslxUtf8 = URLDecoder.decode(wslx, "utf-8");
                preciseConditions.put("wslx", wslxUtf8.trim());
            }
            if (cpry.trim().length() != 0) {
                String cpryUtf8 = URLDecoder.decode(cpry, "utf-8");
                ambiguousConditions.put("spry", cpryUtf8.trim());
            }
            if (flyj.trim().length() != 0) {
                String flyjUtf8 = URLDecoder.decode(flyj, "utf-8");
                ambiguousConditions.put("flyj", flyjUtf8.trim());
            }
            if (cpnf.trim().length() != 0) {
                String cpnfUtf8 = URLDecoder.decode(cpnf, "utf-8");
                preciseConditions.put("cpnf", cpnfUtf8.trim());
            }
            if (ay.trim().length() != 0) {
                String ayUtf8 = URLDecoder.decode(ay, "utf-8").trim();
                preciseConditions.put("ay", ayUtf8);
            }
            if (dsr.trim().length() != 0) {
                String dsrUtf8 = URLDecoder.decode(dsr, "utf-8").trim();
                ambiguousConditions.put("dsr", dsrUtf8);
            }
            if (fymc.trim().length() != 0) {
                String fymcUtf8 = URLDecoder.decode(fymc, "utf-8").trim();
                ambiguousConditions.put("fymc", fymcUtf8);
            }
            cprqbeginUtf8 = URLDecoder.decode(cprqbegin, "utf-8").trim();
            cprqendUtf8 = URLDecoder.decode(cprqend, "utf-8").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        hashMap = esService.getGroupStatistics(preciseConditions, ambiguousConditions, cprqbeginUtf8.trim(), cprqendUtf8.trim(), groupName, whereName, whereValue);
        String str = JSON.toJSONString(hashMap);
        System.out.println(str);
        return str;
    }

}
