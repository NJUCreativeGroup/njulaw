package com.wssearch.controller;

import com.wssearch.service.ESService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by cristph on 2017/4/7.
 */

@Controller
public class DownloadController {

    static final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M

    @Resource
    ESService esService;

    private String getRealFilePath(String path) {
        int pos = path.indexOf("Desktop\\");
        path = "C:\\文书\\" + path.substring(pos + 8);
        System.out.println("download file[" + path + "]");
        return path;
    }

    private String getSingleRealFilePath(String path) {
        int pos = path.indexOf("Desktop\\");
        if (path.indexOf("刑事一审案件") > 0) {
            path = "C:\\文书\\刑事一审案件\\" + path.substring(pos + 8);
        } else {
            path = "C:\\文书\\" + path.substring(pos + 8);
        }
        System.out.println("download file[" + path + "]");
        return path;
    }

    @RequestMapping(value = "downloadAll")
    public String downloadAll(@RequestParam("qwjs") String qwjs,//全文检索
                              @RequestParam("qwjsInput") String qwjsInput,//全文检索输入
                              @RequestParam("ay") String ay,//案由
                              @RequestParam("ah") String ah,//案号
                              @RequestParam("ajmc") String ajmc,//案件名称
                              @RequestParam("fymc") String fymc,//法院名称
                              @RequestParam("fycj") String fycj,//法院层级
                              @RequestParam("ajlx") String ajlx,//案件类型
                              @RequestParam("spcx") String spcx,//审判程序
                              @RequestParam("wslx") String wslx,//文书类型
                              @RequestParam("cprqbegin") String cprqbegin,//裁判日期
                              @RequestParam("cprqend") String cprqend,
                              @RequestParam("cpry") String cpry,
                              @RequestParam("dsr") String dsr,//当事人
                              @RequestParam("flyj") String flyj,//法律依据
                              @RequestParam("cpnf") String cpnf,//裁判年份
                              @RequestParam("type") String type,
                              //　HttpServletRequest对象代表客户端的请求，当客户端通过HTTP协议访问服务器时，HTTP请求头中的所有信息都封装在这个对象中，通过这个对象提供的方法，可以获得客户端请求的所有信息。
                              HttpServletRequest request,
                              //向客户端发送数据，代表服务器的相应
                              HttpServletResponse response) throws ServletException, IOException {
        //精确定位
        HashMap<String, String> preciseConditions = new HashMap<>();
        //模糊定位
        HashMap<String, String> ambiguousConditions = new HashMap<>();

        String cprqbeginUtf8 = null;
        String cprqendUtf8 = null;
        //进行一个转码
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String zipName = sdf.format(new Date()) + ".zip";
        String outFilePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        File zipFile = esService.generateZip(preciseConditions, ambiguousConditions, cprqbeginUtf8, cprqendUtf8, outFilePath + zipName, type);

        downloadFile(zipFile, response, true);
        return "Download Success";
    }

    //下载部分
    @RequestMapping(value = "downloadSome")
    public String downloadSome(@RequestParam("qwjs") String qwjs,//全文检索
                               @RequestParam("qwjsInput") String qwjsInput,//全文检索输入
                               @RequestParam("ay") String ay,//案由
                               @RequestParam("ah") String ah,//案号
                               @RequestParam("ajmc") String ajmc,//案件名称
                               @RequestParam("fymc") String fymc,//法院名称
                               @RequestParam("fycj") String fycj,//法院层级
                               @RequestParam("ajlx") String ajlx,//案件类型
                               @RequestParam("spcx") String spcx,//审判程序
                               @RequestParam("wslx") String wslx,//文书类型
                               @RequestParam("cprqbegin") String cprqbegin,//裁判日期
                               @RequestParam("cprqend") String cprqend,
                               @RequestParam("cpry") String cpry,
                               @RequestParam("dsr") String dsr,//当事人
                               @RequestParam("flyj") String flyj,//法律依据
                               @RequestParam("cpnf") String cpnf,//裁判年份
                               @RequestParam("type") String type,
                               //?是否需要遍历
                               @RequestParam("start") String start,//开始index
                               @RequestParam("end") String end,//endindex
                               HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {

        //精确定位
        HashMap<String, String> preciseConditions = new HashMap<>();
        //模糊定位
        HashMap<String, String> ambiguousConditions = new HashMap<>();

        String cprqbeginUtf8 = null;
        String cprqendUtf8 = null;
        //进行一个转码
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String zipName = sdf.format(new Date()) + ".zip";
        String outFilePath = request.getSession().getServletContext().getRealPath("/") + "upload/";

        int beginIndex = Integer.parseInt(start);
        int listNum = Integer.parseInt(end) - beginIndex + 1;
        File zipFile = esService.generateZip(preciseConditions, ambiguousConditions, cprqbeginUtf8, cprqendUtf8, outFilePath + zipName, type, beginIndex, listNum);

        downloadFile(zipFile, response, true);
        return "Download Success";
    }


    @RequestMapping(value = "/singleDownload")
    public String singleDownload(@RequestParam("path") String path,
                                 @RequestParam("fileName") String fileName,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        String fileNameUtf8 = URLDecoder.decode(fileName, "utf-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileNameUtf8.getBytes("UTF-8"), "ISO-8859-1"));
        int pos = path.indexOf("案件");
        if (pos < 0) {
            pos = path.indexOf("doc");
            String str1 = path.substring(0, pos);
            String str2 = path.substring(pos);
            path = str1 + "刑事一审案件\\" + str2;
        }
        File file = new File(getSingleRealFilePath(path));
        downloadFile(file, response, false);
        return null;
    }


    @RequestMapping(value = "/downloadZip")
    public String downloadFiles(@RequestParam("paths") String paths,
                                HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        List<File> files = new ArrayList<File>();
        String pathsUtf8 = URLDecoder.decode(paths, "utf-8");
        String path[] = pathsUtf8.split("\\|");
        for (String str : path) {
            File temp = new File(getRealFilePath(str));
            if (!temp.exists()) {
                System.out.println("Not exists! --- " + getRealFilePath(str));
                continue;
            }
            files.add(temp);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String fileName = sdf.format(new Date()) + ".zip";
        // 在服务器端创建打包下载的临时文件
        String outFilePath = request.getSession().getServletContext().getRealPath("/") + "upload/";

        File fileZip = new File(outFilePath + fileName);
        // 文件输出流
        FileOutputStream outStream = new FileOutputStream(fileZip);
        // 压缩流
        ZipOutputStream toClient = new ZipOutputStream(outStream);

        zipFile(files, toClient);
        toClient.close();
        outStream.close();
        this.downloadFile(fileZip, response, true);
        return "Download Success";
    }

    //将文件列表压缩
    public static void zipFile(List<File> files, ZipOutputStream outputStream) throws IOException, ServletException {
        try {
            int size = files.size();
            // 压缩列表中的文件
            for (int i = 0; i < size; i++) {
                File file = files.get(i);
                zipFile(file, outputStream);
                System.out.println("have put file No." + i + " into zip");
            }
        } catch (IOException e) {
            throw e;
        }
    }

    //将单个文件放入压缩包
    public static void zipFile(File inputFile, ZipOutputStream zipOutputStream) throws IOException, ServletException {
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
                }
            } else {
                throw new ServletException("文件不存在！");
            }
        } catch (IOException e) {
            throw e;
        }
    }

    //下载文件
    public void downloadFile(File file, HttpServletResponse response, boolean isDelete) {
        try {
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            //是否将生成的服务器端文件删除
            if (isDelete) {
                file.delete();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
