package com.example.lyy.util.word.doc;

import com.example.lyy.entity.Cata;
import com.example.lyy.entity.Detail;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.*;


@Slf4j
public class FreeMarkerUtil {


    private static final String ENCODING = "UTF-8";
    private static Configuration cfg = new Configuration();

    //初始化cfg
    static {
        //设置模板所在文件夹
        cfg.setClassForTemplateLoading(FreeMarkerUtil.class, "/static");
        // setEncoding这个方法一定要设置国家及其编码，不然在ftl中的中文在生成html后会变成乱码
        cfg.setEncoding(Locale.getDefault(), ENCODING);
        // 设置对象的包装器
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        // 设置异常处理器,这样的话就可以${a.b.c.d}即使没有属性也不会出错
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

    }

    //获取模板对象
    public static Template getTemplate(String templateFileName) throws IOException {
        return cfg.getTemplate(templateFileName, ENCODING);
    }

    /**
     * 据数据及模板生成文件
     *
     * @param data             Map的数据结果集
     * @param templateFileName ftl模版文件名
     * @param outFilePath      生成文件名称(可带路径)
     */
    public static File createFile(Map<String, Object> data, String templateFileName, String outFilePath) {
        Writer out = null;
        File outFile = new File(outFilePath);
        try {
            // 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
            Template template = getTemplate(templateFileName);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new OutputStreamWriter(new FileOutputStream(outFile), ENCODING);
            // 处理模版
            template.process(data, out);
            out.flush();
            log.info("由模板文件" + templateFileName + "生成" + outFilePath + "成功.");
        } catch (Exception e) {
            log.error("由模板文件" + templateFileName + "生成" + outFilePath + "失败");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                log.error("关闭Write对象出错", e);
                e.printStackTrace();
            }
        }
        return outFile;
    }

    //获得图片的base64码
    public static String getImageBase(String src) throws Exception {
        if (src == null || src == "") {
            return "";
        }
        File file = new File(src);
        if (!file.exists()) {
            System.out.println(src);
            return "";
        }
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static void main(String[] args) {
        try {
            Map<String,Object> map = new HashMap<>();

//            List<Image> stringList = new ArrayList<>();
//            for (int i = 0; i <= 28; i++) {
//                Image build = Image.builder().name(UUID.randomUUID().toString()).data(getImageBase("C:\\Users\\Administrator\\Desktop\\pic\\" + (i + 1) + ".jpg")).build();
//                stringList.add(build);
//            }
//            List list = groupListByQuantity(stringList, 2);
//            System.out.println(list.size());

            map.put("name","sssssss");
//
//            Map map1 = spiltList(stringList, 2);
//            List list = groupListByQuantity(stringList, 2);
//            Map<String,Object> map = new HashMap<>();
//            List<Map<String, List<Cata>>> data = getData();
//            System.out.println(JSON.toJSON(data).toString());
//            map.put("baes64img",list);
//            map.put("list",data);
//            map.put("image",Image.builder().name(String.valueOf(System.currentTimeMillis())).data(getImageBase("C:\\\\Users\\\\Administrator\\\\Desktop\\\\1.jpg")).build());
//            List<Image> list = new ArrayList<>();
//            for (int i = 0; i < 5; i++) {
//                list.add(Image.builder().name(String.valueOf(System.currentTimeMillis())+i).data(getImageBase("C:\\\\Users\\\\Administrator\\\\Desktop\\\\1.jpg")).build());
//            }
//            Map<String,Object> map = new HashMap<>();
//            map.put("images",list);
            File file = createFile(map, "1.ftl", "C:/Users/Administrator/Desktop/pic.doc");
//            GeneWordTitle.printDoc(file);

            /*List<Map<String, String>> educations = new ArrayList<Map<String, String>>();
            Map<String, String> paramsMap = new HashMap<String, String>();
            paramsMap.put("school", "禹州一高");
            paramsMap.put("startDate", "2008-09");
            paramsMap.put("endDate", "2012-06");
            paramsMap.put("person", "李磊");
            Map<String, String> paramsMap2 = new HashMap<String, String>();
            paramsMap2.put("school", "江西科技师范大学");
            paramsMap2.put("startDate", "2012-09");
            paramsMap2.put("endDate", "2016-07");
            paramsMap2.put("person", "李杰");
            educations.add(paramsMap);
            educations.add(paramsMap2);
            data.put("educations", educations);*/
//            Map<String,String> imageMap = new HashMap<>();
//            imageMap.put("1",getImageBase("C:\\Users\\Administrator\\Desktop\\1.jpg"));
//            imageMap.put("2",getImageBase("C:\\Users\\Administrator\\Desktop\\2.jpg"));
//            data.put("images", imageMap);
//            crateFile(data, "cata.ftl", "C:/Users/Administrator/Desktop/简历.doc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String,List<Cata>>> getData(){
        List<Map<String,List<Cata>>> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Cata> list = new ArrayList<>();

            List<Detail> objects = new ArrayList<>();
            objects.add(Detail.builder().info("1111111111").build());
            objects.add(Detail.builder().info("222222222222").build());
            objects.add(Detail.builder().info("33333333333").build());
            list.add(Cata.builder().name("11111").info("122222").cata("211111").bool(null).build());
            list.add(Cata.builder().name("11111").info("122222").cata("222222").bool(false).build());
            list.add(Cata.builder().name("11111").info("133333").cata("233333").bool(null).build());
            list.add(Cata.builder().name("11111").info("133333").cata("333333").bool(false).build());
            list.add(Cata.builder().name("22222").info("211111").cata("41111").bool(null).build());
            list.add(Cata.builder().name("22222").info("211111").cata("42222").bool(null).build());
            list.add(Cata.builder().name("22222").info("222222").cata("43333").bool(null).build());
            list.add(Cata.builder().name("22222").info("222222").cata("44444").bool(null).build());
            list.add(Cata.builder().name("33333").info("asdasdas").cata("brebwcwec").bool(true).build());
            list.add(Cata.builder().name("33333").info("asdasdas").cata("fewgvqevq").bool(false).build());
            list.add(Cata.builder().name("33333").info("cascasc").cata(",hmhgnrtn").bool(null).build());
            list.add(Cata.builder().name("33333").info("cascasc").cata("fwevwewcwec").bool(false).build());
            list.add(Cata.builder().name("44444").info("cqwcqcsa").cata("kijtyjnyj").bool(null).build());
            list.add(Cata.builder().name("44444").info("cqwcqcsa").cata("ewfweqxs").bool(null).build());
            list.add(Cata.builder().name("44444").info("qwqwcqwc").cata("ewfwefecq").bool(null).build());
            list.add(Cata.builder().name("44444").info("qwqwcqwc").cata("fhwewfqwe").bool(null).build());
            Map<String, List<Cata>> map = new HashMap<>();
            map.put("map"+i,list);
            dataList.add(map);
        }

        return dataList;
    }


    /*public static void xmlToWord(String docfile, String htmlfile) {
        ActiveXComponent app = null;
        try {
            app = new ActiveXComponent("Word.Application"); // 启动word
            app.setProperty("Visible", new Variant(true));
            Dispatch docs = app.getProperty("Documents").toDispatch();
            Dispatch doc = Dispatch.invoke(
                    docs,
                    "Open",
                    Dispatch.Method,
                    new Object[]{docfile, new Variant(false),
                            new Variant(true)}, new int[1]).toDispatch();
            Dispatch selection = app.getProperty("Selection").toDispatch();
            Dispatch find = Dispatch.call(selection, "Find").toDispatch();
            *//* 设置要查找的内容 *//*
            Dispatch.put(find, "Text", "目录");
            *//* 向前查找 *//*
            Dispatch.put(find, "Forward", "True");
            *//* 设置格式 *//*
            Dispatch.put(find, "Format", "True");
            *//* 大小写匹配 *//*
            Dispatch.put(find, "MatchCase", "True");
            *//* 全字匹配 *//*
            Dispatch.put(find, "MatchWholeWord", "True");
            *//* 查找并选中 *//*
            boolean execute = Dispatch.call(find, "Execute").getBoolean();

            *//* 取得ActiveDocument、TablesOfContents、range对象 *//*
            Dispatch ActiveDocument = app.getProperty("ActiveDocument").toDispatch();
            Dispatch TablesOfContents = Dispatch.get(ActiveDocument, "TablesOfContents").toDispatch();
            Dispatch.call(selection, "MoveRight");
            Dispatch.call(selection, "TypeParagraph"); //换行
            Dispatch range = Dispatch.get(selection, "Range").toDispatch();

            *//* 增加目录 *//*
            Dispatch.call(TablesOfContents, "Add", range, new Variant(true), new Variant(1), new Variant(3), new Variant(true), new Variant(""), new Variant(true), new Variant(true));
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[]{htmlfile, new Variant()}, new int[1]);
            Variant f = new Variant(false);
            Dispatch.call(doc, "Close", f);

        } finally {
            if (app != null) app.invoke("Quit", new Variant[]{});
        }
    }*/

}