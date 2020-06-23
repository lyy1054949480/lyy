package com.example.lyy.util;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.ClassUtils;

//@Configuration
public class MyAllEncompassingFormHttpMessageConverter extends MyFormHttpMessageConverter {
    private static final boolean jaxb2Present = ClassUtils.isPresent("javax.xml.bind.Binder", org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter.class.getClassLoader());
    private static final boolean jackson2Present = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter.class.getClassLoader()) && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter.class.getClassLoader());
    private static final boolean jackson2XmlPresent = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter.class.getClassLoader());
    private static final boolean gsonPresent = ClassUtils.isPresent("com.google.gson.Gson", org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter.class.getClassLoader());

    public MyAllEncompassingFormHttpMessageConverter() {
        this.addPartConverter(new SourceHttpMessageConverter());
        if (jaxb2Present && !jackson2XmlPresent) {
            this.addPartConverter(new Jaxb2RootElementHttpMessageConverter());
        }

        if (jackson2Present) {
            this.addPartConverter(new MappingJackson2HttpMessageConverter());
        } else if (gsonPresent) {
            this.addPartConverter(new GsonHttpMessageConverter());
        }

        if (jackson2XmlPresent) {
            this.addPartConverter(new MappingJackson2XmlHttpMessageConverter());
        }
    }
}