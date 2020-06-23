package com.example.lyy.util.io;

import org.apache.tika.Tika;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.net.URL;

/**
 * @author : zhouxd
 * @date : 2018/12/24 08:54
 */
public class CheckFileTypeUtils {

    /**
     * 获取文件后缀名
     *
     * @param url 文件url
     * @return 后缀名
     */
    public static String getFileExtensionByUrl(URL url) {
        try {
            return getFileExtensionByType(getFileTypeByUrl(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回文件类型
     *
     * @param url 文件url地址
     * @return 文件类型
     */
    public static String getFileTypeByUrl(URL url) {
        try {
            Tika tika = new Tika();
            return tika.detect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取文件后缀名
     *
     * @param type 文件类型
     * @return 后缀名
     */
    public static String getFileExtensionByType(String type) {
        try {
            MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
            MimeType tikaMimeType = allTypes.forName(type);
            return tikaMimeType.getExtension();
        } catch (MimeTypeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
