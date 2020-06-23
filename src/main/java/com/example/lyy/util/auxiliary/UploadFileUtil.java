package com.example.lyy.util.auxiliary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
public class UploadFileUtil {

    public static void  uploadFile(MultipartFile multipartFile){
        if (multipartFile != null){
            String tempFilePath = System.getProperty("java.io.tmpdir") + multipartFile.getOriginalFilename();
            File file = new File(tempFilePath);
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                log.error("生产临时文件失败，{}",e.getMessage());
                e.printStackTrace();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8"));

        }
    }
}
