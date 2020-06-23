package com.example.lyy.util.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Map;

/**
 * @ProjectName: travel-report
 * @Package: com.zjhcsoft.travel.report.util
 * @ClassName: FileIUtils
 * @Author: zhangchao
 * @Description: 文件工具类
 * @Date: 2020/1/13 11:45
 * @Version: 1.0
 */
public class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
    private final static String SEPARATOR = File.separator;

    /**
     * Base64字符串转为字节数组
     * @param encodeData
     * @return
     */
    public  static byte[] baseTobyte(String encodeData) {
        byte[] data;
        try {
            //解码
            BASE64Decoder decoder = new BASE64Decoder();
            data = decoder.decodeBuffer(encodeData);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  Base64字节数组转为文件
     * @param model
     * @throws IOException
     */
    public static void baseToFile(Map<String, Object> model) throws IOException {
        String encodeData = model.get("chartPic").toString();
        String path = model.get("path").toString();
        String dirPath = path.substring(0, path.lastIndexOf(SEPARATOR) + 1);
        FileOutputStream fos = null;
        try {
            File tempPath = new File(dirPath);
            if (!tempPath.exists()) {
                LOGGER.info("-----------------------tempPath不存在");
                tempPath.mkdirs();
                LOGGER.info("-----------------------tempPath创建成功：{}", tempPath.getPath());
            }
            byte[] fileEncode = baseTobyte(encodeData);
            fos = new FileOutputStream(path);
            fos.write(fileEncode);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fos.close();
        }
    }

    /**
     * 获取文件Base64字节码字符串
     * @param url
     * @return
     */
    public static String getImageStr(String url) throws IOException{
        InputStream in = null;
        byte[] data = null;
        File file = new File(url);
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            BASE64Encoder encoder = new BASE64Encoder();
            String encodeData = encoder.encode(data);
            return encodeData;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            in.close();
        }
        return null;
    }
}
