package com.example.lyy.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.lyy.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.servlet.Servlet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ReadJson {

    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:1.json");
        String fileName = null;
        fileName = resource.getFilename();
        log.info("loading file " + fileName);
        InputStream ins = null;
        ByteArrayOutputStream bos = null;
        try {
            ins = resource.getInputStream();
            bos = new ByteArrayOutputStream();
            IOUtils.copy(ins, bos);
            String str = StringUtil.bytes2Str(bos.toByteArray());
            JSONObject jsonObject = JSONObject.parseObject(str);
            ins.close();
            bos.close();
            System.out.println(JSON.toJSON(jsonObject));
        } catch (Exception e) {
            log.error("", e);
        } finally {
            IOUtils.closeQuietly(ins);
            IOUtils.closeQuietly(bos);
        }
    }
}
