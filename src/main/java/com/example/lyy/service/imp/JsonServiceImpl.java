package com.example.lyy.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lyy.domain.PromptingMessageEnum;
import com.example.lyy.entity.TFullpathElementDef;
import com.example.lyy.entity.TOrder;
import com.example.lyy.mapper.TFullpathElementDefMapper;
import com.example.lyy.mapper.TOrderMapper;
import com.example.lyy.service.JsonService;
import com.example.lyy.util.id.IDMaker;
import com.example.lyy.util.system.VisitorOpreator;
import com.example.lyy.util.xml.JsonXmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class JsonServiceImpl implements JsonService {

    @Autowired
    private TFullpathElementDefMapper elementDefMapper;

    @Autowired
    private TOrderMapper tOrderMapper;

    private Map<String, List<TFullpathElementDef>> map;

    //@PostConstruct
    public void searchAndPack() {
        List<TFullpathElementDef> tFullpathElementDefs = elementDefMapper.selectAll();
        tFullpathElementDefs = tFullpathElementDefs.stream().filter(Objects::nonNull).collect(Collectors.toList());
        map = packFullElement(tFullpathElementDefs);
        log.info("===============初始化父节点为键的map===============");
        log.info("=====map的长度:{}=====",map.size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject insertNewJson(String json, HttpServletRequest request) throws SAXException {
        Map jsonMap = (Map) JSON.parse(json);
        StringBuilder stringBuilder = new StringBuilder();
        JSONObject jsonObject = new JSONObject();
        JSONObject temp = new JSONObject();
        jsonObject = generateNewJson(jsonObject, null, map, null, jsonMap, stringBuilder);
        if(StringUtils.isEmpty(stringBuilder.toString())){
            int insert = tOrderMapper.insert(TOrder.builder()
                            .orderId(IDMaker.getOrderCode())
                            .contextMsg(JsonXmlUtils.JsonToXml(jsonObject))
                            .operator(getVisitorOption(request))
                            .build());
            if(insert!=1){
                temp.put("msg", PromptingMessageEnum.MESSAGE_28.getMessage());
            }
        }else{
            temp.put("msg", stringBuilder.substring(0,stringBuilder.length()-1));
        }
        if(temp.isEmpty()){
            return jsonObject;
        }
        return temp;
    }

    /**将参数中的数据以上下文的格式存入新的json
     * @param jsonObject 存储数据的新json
     * @param fatherNode 父节点
     * @param map 多个父节点对应的子集合
     * @param contextNode 上下文节点
     * @param jsonMap 参数转成的map
     * @param stringBuilder 用于存储参数校验的容器
     * @return
     */
    private JSONObject generateNewJson(JSONObject jsonObject,String fatherNode,Map<String, List<TFullpathElementDef>> map,String contextNode,Map jsonMap,StringBuilder stringBuilder) {
        List<TFullpathElementDef> defList = new ArrayList<>();
        /**
         * 判断当前次循环遍历的是哪个父节点，第一次默认为root
         * 每次遍历父节点下对应的子集合
         */
        if(StringUtils.isEmpty(fatherNode)){
            defList = map.get("root");
        }else{
            defList = map.get(fatherNode);
        }
        for (TFullpathElementDef tFullpathElementDef : defList) {
            String initContextPath = tFullpathElementDef.getFullpathElement();
            String contextPath = tFullpathElementDef.getFullpathElement();
            String jsonPath = tFullpathElementDef.getJsonItemPath();
            /**
             * 如果contextNode不为空,说明当前遍历的是上下文中的数组,节点为数组时判断 (递归时传入contextNode)
             */
            if(!StringUtils.isEmpty(contextNode)){
                contextPath = contextPath.substring(contextNode.length() + 1);
            }
            /**
             * 当前次循环寻找的key JsonItemPath的最后一位
             */
            String jsonKey = null;
            if(jsonPath.contains(".")){
                String[] split = jsonPath.split("\\.");
                jsonKey = split[split.length-1];
            }else{
                jsonKey = jsonPath;
            }
            /**
             * 校验参数中是否符合数据库配置条件
             */
            if(!jsonMap.containsKey(jsonKey)){
                if("Y".equals(tFullpathElementDef.getIsmust()) && StringUtils.isEmpty(jsonMap.get(jsonKey))){
                    stringBuilder.append(tFullpathElementDef.getIntroduce()+"不能为空,");
                }
                continue;
            }
            /**
             * 获取上下文加点路径，将参数中对应节点的值存入新json
             */
            String[] contextSpilt = contextPath.split("\\.");
            /**
             * 判断为字符串时
             */
            if("00".equals(tFullpathElementDef.getValueType())){
                /**
                 * 处理主字段
                 */
                if("00".equals(tFullpathElementDef.getColType())){
                    /**
                     * 防止脚本字段
                     */
                    if(contextSpilt.length==1){
                        if("Y".equals(tFullpathElementDef.getIsmust()) && StringUtils.isEmpty(jsonMap.get(jsonKey))){
                            stringBuilder.append(tFullpathElementDef.getIntroduce()+"不能为空,");
                        }else{
                            jsonObject.put(contextPath,jsonMap.get(jsonKey));
                        }
                    }else{
                        JSONObject temp = null;
                        temp = jsonObject;
                        for (int i = 0; i < contextSpilt.length-1; i++) {
                            if(!temp.containsKey(contextSpilt[i])){
                                temp.put(contextSpilt[i],new JSONObject());
                            }
                            temp = (JSONObject) temp.get(contextSpilt[i]);
                        }
                        if("Y".equals(tFullpathElementDef.getIsmust()) && StringUtils.isEmpty(jsonMap.get(jsonKey))){
                            stringBuilder.append(tFullpathElementDef.getIntroduce()+"不能为空,");
                        }else{
                            temp.put(contextSpilt[contextSpilt.length-1],jsonMap.get(jsonKey));
                        }
                    }
                }
                /**
                 * 处理扩展字段 放在extendList对象下
                 */
                else{
                    JSONArray array = new JSONArray();
                    JSONObject temp = null;
                    temp = jsonObject;
                    /**
                     * 防止脚本字段
                     */
                    if(contextSpilt.length!=2){
                        for (int i = 0; i < contextSpilt.length - 2; i++) {
                            if(!temp.containsKey(contextSpilt[i])){
                                temp.put(contextSpilt[i],new JSONObject());
                            }
                            temp = (JSONObject) temp.get(contextSpilt[i]);
                        }
                        JSONObject object = new JSONObject();
                        if(!temp.containsKey("extendList")){
                            temp.put("extendList",new JSONArray());
                        }
                        array = (JSONArray)temp.get("extendList");
                        object.put("key",contextSpilt[contextSpilt.length-1]);
                        if("Y".equals(tFullpathElementDef.getIsmust()) && StringUtils.isEmpty(jsonMap.get(jsonKey))){
                            stringBuilder.append(tFullpathElementDef.getIntroduce()+"不能为空,");
                        }else{
                            object.put("value",jsonMap.get(jsonKey));
                        }
                        array.add(object);
                    }
                }
            }else if("01".equals(tFullpathElementDef.getValueType())){
                /**
                 * 判断为对象时 递归调用  修改jsonMap
                 */
                log.info("===============节点下为对象时,开始递归===============");
                generateNewJson(jsonObject,jsonPath,map,null,(Map) jsonMap.get(jsonKey),stringBuilder);
            }else{
                /**
                 *
                 * 判断为数组时 递归调用  修改jsonMap
                 */
                JSONArray jsonArray = new JSONArray();
                List<Map> list = (List<Map>) jsonMap.get(jsonKey);
                if (!CollectionUtils.isEmpty(list)){
                    for (Map arrayMap : list) {
                        log.info("===============节点下为数组时,开始递归===============");
                        jsonArray.add(generateNewJson(jsonObject,jsonKey,map,initContextPath,(Map) arrayMap.get(jsonKey),stringBuilder));
                    }
                }
            }
        }
        return jsonObject;
    }

    /**
     * 以父节点将参数分组
     * @param list
     * @return
     */
    private Map<String,List<TFullpathElementDef>> packFullElement(List<TFullpathElementDef> list){
        return list.stream().collect(Collectors.groupingBy(TFullpathElementDef::getParentPath));
        /*Map<String,List<TFullpathElementDef>> map = new HashMap<>();
        for (TFullpathElementDef tFullpathElementDef : list) {
            String parentPath = tFullpathElementDef.getParentPath();
            if(map.containsKey(parentPath)){
                *//**
                 * 父节点存在直接取出对应的list，存入当前对象
                 *//*
                List<TFullpathElementDef> defList = map.get(parentPath);
                defList.add(tFullpathElementDef);
            }else{
                *//**
                 * 父节点不存在新建list存入当前对象，将list以父节点为键存入map
                 *//*
                List<TFullpathElementDef> defList = new ArrayList<>();
                defList.add(tFullpathElementDef);
                map.put(parentPath,defList);
            }
        }
        return map;*/
    }

    private String getVisitorOption(HttpServletRequest request){
        String requestSystemInfo = VisitorOpreator.getRequestSystemInfo(request);
        String ipAddress = VisitorOpreator.getIpAddress(request);
        String hostName = VisitorOpreator.getHostName(ipAddress);
        String requestBrowserInfo = VisitorOpreator.getRequestBrowserInfo(request);
        return "操作系统:"+requestSystemInfo + ",主机名:"+hostName + ",IP"+ipAddress + ",浏览器"+requestBrowserInfo;
    }
}
