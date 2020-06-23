package com.example.lyy.util.jfreechart;

import com.example.lyy.controller.TestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

@Slf4j
public class GetFontUtil {
    static Font font = null;

    public static void initFont() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("static/111.ttf");
        File file = classPathResource.getFile();
        System.out.println(file.getAbsolutePath());
        FileInputStream  fileInputStream = new FileInputStream(file);
        font = Font.createFont(Font.TRUETYPE_FONT, fileInputStream);
        fileInputStream.close();
        GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        localGraphicsEnvironment.registerFont(font);
        System.out.println("`````````````````````````"+font.getFontName());
    }

    public static Font getFont(int style, Float size) {
        try {
            initFont();
            Font nf = font.deriveFont(style,size);
            return nf;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
