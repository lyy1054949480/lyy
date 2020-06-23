package com.example.lyy.service;

import com.alibaba.fastjson.JSONObject;
import com.example.lyy.entity.TFullpathElementDef;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface JsonService {


    public JSONObject insertNewJson(String json, HttpServletRequest request) throws SAXException;
}
