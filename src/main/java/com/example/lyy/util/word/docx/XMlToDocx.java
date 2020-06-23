package com.example.lyy.util.word.docx;

import com.example.lyy.util.word.doc.FreeMarkerUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class XMlToDocx {

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


    private static String OS = System.getProperty("os.name").toLowerCase();

    /**
     * 根据数据生成文本
     * @param dataMap 数据
     * @param outFilePath 生成的document.xml和document.xml.rels对应的目录名称
     * @param template 模板对象
     * @throws Exception
     */
    private static  void toText(Map<String,Object> dataMap, String outFilePath, Template template)throws Exception  {
        File docFile = new File(outFilePath);
        FileOutputStream fos = new FileOutputStream(docFile);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos),10240);
        template.process(dataMap,out);
        if(out != null){
            out.close();
        }
    }

    /**
     * 生成word docx
     * @param dataMap 数据
     * @param ftlPath  模板存放的目录（修改后的doc.xml）
     * @param docFilePath 生成的document.xml和document.xml.rels对应的目录名称（输出位置）
     * @param fileList fileList 图片文件
     * @throws Exception
     */
    public static  void makeWord(Map<String,Object> dataMap,String ftlPath,String docFilePath,  List<File> fileList) throws Exception {
        /** 初始化配置文件 **/
        Configuration configuration = new Configuration();
        String fileDirectory = ftlPath;
        /** 加载文件 **/
        File file = new File(fileDirectory);
        configuration.setDirectoryForTemplateLoading(file);

        /** 加载模板 **/
        Template template = configuration.getTemplate("document.xml");
//        Template template = getTemplate("document.xml");
        /** 指定输出word文件的路径 **/
        String outFilePath = docFilePath+".xml";
        toText(dataMap,outFilePath,template);

        template = configuration.getTemplate("document.xml.rels");
        outFilePath = docFilePath+".xml.rels";
        toText(dataMap,outFilePath,template);

        try {
            ZipInputStream zipInputStream = ZipUtils.wrapZipInputStream(new FileInputStream(new File(fileDirectory+File.separator+"report.zip")));//该zip文件是docx重命名后的压缩文件
            ZipOutputStream zipOutputStream = ZipUtils.wrapZipOutputStream(new FileOutputStream(new File(docFilePath+".docx")));
            File fileText = new File(docFilePath+".xml");
            File fileImg = new File(docFilePath+".xml.rels");
            ZipUtils.replaceItem(zipInputStream, zipOutputStream, new FileInputStream(fileText), new FileInputStream(fileImg),fileList);
            if(fileText.exists()){
                fileText.delete();
            }
            if(fileImg.exists()){
                fileImg.delete();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 生成pdf
     */
//    public static  void makePdfByXcode(String ftlPath,String docFilePath){
//        try {
//            XWPFDocument document=new XWPFDocument(new FileInputStream(new File(docFilePath+".docx")));
//            File outFile=new File(docFilePath+".pdf");
//                if(!outFile.getParentFile().exists()){
//                    outFile.getParentFile().mkdirs();
//            }
//            OutputStream out=new FileOutputStream(outFile);
//            PdfOptions options= PdfOptions.getDefault();
//            IFontProvider iFontProvider = new IFontProvider() {
//                @Override
//                public Font getFont(String familyName, String encoding, float size, int style, Color color) {
//                    try {
//                        BaseFont bfChinese = null;
//                        if( OS.indexOf("linux")>=0){
//                            bfChinese =  BaseFont.createFont(ftlPath+"/font/msyh.ttf", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//                        }else{
//                            bfChinese =  BaseFont.createFont("C:/WINDOWS/Fonts/STSONG.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//                        }
//                        Font fontChinese = new Font(bfChinese, size, style, color);
//                        if (familyName != null)
//                            fontChinese.setFamily(familyName);
//                        return fontChinese;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        return null;
//                    }
//                }
//            };
//            options.fontProvider( iFontProvider );
//            PdfConverter.getInstance().convert(document,out,options);
//
//        }
//        catch (  Exception e) {
//            e.printStackTrace();
//        }
//    }


}