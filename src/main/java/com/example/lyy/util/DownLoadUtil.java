package com.example.lyy.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class DownLoadUtil {

    public static void downLoadFile(File file, String fileName,String fileType, HttpServletResponse response) throws Exception {

        try(
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());

        ){
            response.setContentLength(bufferedInputStream.available());
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(fileName + "." + fileType, "utf-8"));
            IOUtils.copy(bufferedInputStream, bufferedOutputStream);
            bufferedOutputStream.flush();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void downLoadCollectionText(Collection collection, String fileName, String fileType, HttpServletResponse response) throws Exception {
        try(
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        ){
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(fileName + "." + fileType, "utf-8"));
            IOUtils.writeLines(collection,IOUtils.LINE_SEPARATOR,bufferedOutputStream);
            bufferedOutputStream.flush();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public static void upLoadFile(File file, String fileName){

    }

    public static JSONObject getJsonByLocalFile(String name) throws Exception {
        JSONObject jsonObject = null;
        try (
                InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
                ByteArrayOutputStream bufferedOutputStream = new ByteArrayOutputStream();
        ){
            IOUtils.copy(resourceAsStream,bufferedOutputStream);
            bufferedOutputStream.flush();
            byte[] bytes = bufferedOutputStream.toByteArray();
            if (bytes != null && bytes.length > 0){
                String result = new String(bytes, StandardCharsets.UTF_8);
                jsonObject = JSONObject.parseObject(result);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject;
    }

    public static void main(String[] args) throws Exception {
        JSONObject json = getJsonByLocalFile("1.json");
        System.out.println(JSON.toJSONString(json));
    }
}
