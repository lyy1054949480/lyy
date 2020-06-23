package com.example.lyy.util.pdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

/**
 * Created by lux on 2017-11-26.
 */
public class Html2PdfUtil {



    public static final Logger log = LoggerFactory.getLogger(Html2PdfUtil.class);

    /**
     * html转pdf
     * @param srcPath html路径，可以是硬盘上的路径，也可以是网络路径
     * @param destPath pdf保存路径
     * @param toPdfTool wkhtmltopdf可执行程序路径 /windos、linux
     * @return 转换成功返回true
     * @author Dengjx
     */
    public static String convert(String srcPath, String destPath, String toPdfTool) throws IOException {
        log.info("srcPath:"+srcPath);
        log.info("destPath:"+destPath);
        log.info("toPdfTool:"+toPdfTool);
        File file = new File(destPath);

        /**
         *  判断html转pdf文件是否存在
         */
        File wkhtmltopdfFile = new File(toPdfTool);
        System.out.println("文件：------------------" + wkhtmltopdfFile.exists());
        if(wkhtmltopdfFile.exists()){
            wkhtmltopdfFile.setExecutable(true);
        }
//        File parentFile = wkhtmltopdfFile.getParentFile();
//        if(!parentFile.exists()){
//            parentFile.mkdirs();
//        }
//        if(!wkhtmltopdfFile.exists()){
//            wkhtmltopdfFile.createNewFile();
//            wkhtmltopdfFile.canExecute();
//            FileOutputStream fileOutputStream = new FileOutputStream(wkhtmltopdfFile);
//            /**
//             * 不存在去资源中心下载
//             */
//            HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ResService"))
//                    .andCommandKey(HystrixCommandKey.Factory.asKey("FileDownload"))
//                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ResServicePool"))
//                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000));
//            FileDownloadCommand fileDownloadCommand = new FileDownloadCommand(setter,restTemplate,"1390250677145436160");
//            Object execute = fileDownloadCommand.execute();
//            log.info(execute.toString());
//            ResponseEntity<byte[]> forEntity = (ResponseEntity<byte[]>) execute;
//            byte[] body = forEntity.getBody();
//            fileOutputStream.write(body);
//            fileOutputStream.close();
//        }

        File parent = file.getParentFile();
        //如果pdf保存路径不存在，则创建路径
        if(!parent.exists()){
            parent.mkdirs();
        }
        StringBuilder cmd = new StringBuilder();
        cmd.append(toPdfTool);
        cmd.append(" ");
        cmd.append(srcPath);
        cmd.append(" ");
        cmd.append(destPath);
        log.info("命令为：------------------" + cmd.toString());
        try{
            Process proc = Runtime.getRuntime().exec(cmd.toString());
            HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
            HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
            error.start();
            output.start();
            proc.waitFor();
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return destPath;
    }
}
