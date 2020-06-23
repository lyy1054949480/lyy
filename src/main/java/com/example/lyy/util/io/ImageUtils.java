package com.example.lyy.util.io;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author : zhouxd
 * @date : 2018/12/24 17:45
 */
public class ImageUtils {


    /**
     * 下载图片，并压缩
     *
     * @param url      图片url地址
     * @param filePath 压缩完图片路径
     * @return 图片路径
     * @throws Exception
     */
    public static String compressImageByUrl(URL url, String filePath) throws Exception {
        try {
            Thumbnails.of(url).scale(1).outputQuality(0.5).toFile(filePath);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 压缩图片
     *
     * @param inputStream 图片输入流
     * @param filePath    压缩图片路径
     * @return 图片路径
     */
    public static String compressImageByInputStream(InputStream inputStream, String filePath) {
        try {
            Thumbnails.of(inputStream).scale(1).outputQuality(0.5).toFile(filePath);
//            Thumbnails.
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
