package com.example.lyy.util.xml;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.*;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**@author lyy
 * 对象转xml(不需要xml注解)，属性只能有普通类型和List
 */
public class XmlUtil {


    public static String ObjectToXml(String rootTag ,String encoding,Object object)throws Exception{
        Document doc = DocumentFactory.getInstance().createDocument();
        doc.setXMLEncoding(encoding);
        Element root = DocumentHelper.createElement(rootTag);
        Element head = root.addElement("Head");
        Element id = head.addElement("ClassName");
        id.setText(object.getClass().getSimpleName());
        Element Body = root.addElement("Body");
        pojo2Element(Body,object);
        doc.add(root);
        return doc.asXML();
    }

    /**
     *object递归生成element
     * @return
     */
    public static Element pojo2Element(Element element,Object object) throws Exception{
        Method[] methods = object.getClass().getMethods();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Method method : methods) {
            for (Field declaredField : declaredFields) {
                if((method.getName().toLowerCase()).contains(declaredField.getName().toLowerCase()) && (!method.getName().contains("set")) ){
                    if(!(declaredField.getType().toString()).contains("interface")){
                        Element key = element.addElement(toUpperCaseFirstOne(declaredField.getName()));
                        key.setText(checkEmpty(method.invoke(object)));
                    }else{
                        List list = (List)method.invoke(object);
                        if(!CollectionUtils.isEmpty(list)){
                            for (Object o : list) {
                                Element extendElement = element.addElement(o.getClass().getSimpleName());
                                pojo2Element(extendElement,o);
                            }
                        }
                    }
                }
            }
        }
        return element;
    }

    /**
     * 检查是否为空
     * @param obj
     * @return
     */
    public static String checkEmpty(Object obj){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(null == obj){
            return "";
        }else if(obj instanceof Date){
            return sdf.format(obj);
        }else {
            return obj.toString();
        }
    }

    /**
     * Dom转Map
     * @param e
     * @return
     */
    public static Map Dom2Map(Element e){
        Map map;
        map = new HashMap();
        List list = e.elements();
        if(list.size() > 0){
            for (int i = 0;i < list.size(); i++) {
                Element iter = (Element) list.get(i);
                List mapList = new ArrayList();

                if(iter.elements().size() > 0){
                    Map m = Dom2Map(iter);
                    if(map.get(iter.getName()) != null){
                        Object obj = map.get(iter.getName());
                        if(!obj.getClass().getName().equals("java.util.ArrayList")){
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if(obj.getClass().getName().equals("java.util.ArrayList")){
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    }else {
                        map.put(iter.getName(), m);
                    }
                }
                else{
                    if(map.get(iter.getName()) != null){
                        Object obj = map.get(iter.getName());
                        if(!obj.getClass().getName().equals("java.util.ArrayList")){
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if(obj.getClass().getName().equals("java.util.ArrayList")){
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    }else {
                        map.put(iter.getName(), iter.getText());
                    }
                }
            }
        }else{
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    /**
     * 字符串首字母转大写
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0))){
            return s;
        } else{
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 字符串转xml并获取document的根节点
     */
    public static Element string2Element(String xml) throws Exception{
        Document document = DocumentHelper.parseText(xml);
        Element rootElement = document.getRootElement();
        return rootElement;
    }

    /**
     * 字符串转xml
     */
    public static Document string2Xml(String xml) throws Exception{
        return DocumentHelper.parseText(xml);
    }

    /**
     * xml字节数组转json
     * @param xml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public static JSONObject xml2JSON(byte[] xml) throws JDOMException, IOException {
        JSONObject json = new JSONObject();
        InputStream is = new ByteArrayInputStream(xml);
        SAXBuilder sb = new SAXBuilder();
        org.jdom2.Document doc = sb.build(is);
        org.jdom2.Element root = doc.getRootElement();
        json.put(root.getName(), iterateElement(root));
        return json;
    }
    private static JSONObject iterateElement(org.jdom2.Element element) {

        List node = element.getChildren();
        org.jdom2.Element et = null;
        JSONObject obj = new JSONObject();
        List list = null;
        for (int i = 0; i < node.size(); i++) {
            list = new LinkedList();
            et = (org.jdom2.Element) node.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.getChildren().size() == 0)
                    continue;
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(iterateElement(et));
                obj.put(et.getName(), list);
            } else {
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(et.getTextTrim());
                obj.put(et.getName(), list);
            }
        }
        return obj;
    }
}
