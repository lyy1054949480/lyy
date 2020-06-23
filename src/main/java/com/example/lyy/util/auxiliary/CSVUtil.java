package com.example.lyy.util.auxiliary;

import org.apache.commons.beanutils.BeanUtils;

import java.io.*;
import java.util.*;

/**
 * @Description: CVS文件操作工具类
 * @author guohuibin
 * @date 2018/11/16 15:35
 */
public class CSVUtil {
    /**
     * 生成为CVS文件
     *
     * @param exportData 源数据List
     * @param map        csv文件的列表头map
     * @param outPutPath 文件路径
     * @param fileName   文件名称
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static File createCSVFile(List exportData, LinkedHashMap map,
                                     String outPutPath, String fileName) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            File file = new File(outPutPath);
            if (!file.exists()) {
                file.mkdir();
            }
            // 定义文件名格式并创建
            csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
            // UTF-8使正确读取分隔符","
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(csvFile), "GBK"), 1024);
            // 写入文件头部
            for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator
                    .hasNext(); ) {
                Map.Entry propertyEntry = (Map.Entry) propertyIterator
                        .next();
                csvFileOutputStream
                        .write("\"" + (String) propertyEntry.getValue() != null ? (String) propertyEntry
                                .getValue() : "" + "\"");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.newLine();
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext(); ) {
                Object row = (Object) iterator.next();
                for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext(); ) {
                    Map.Entry propertyEntry = (Map.Entry) propertyIterator.next();
                    /*-------------------------------*/
                    //以下部分根据不同业务做出相应的更改
                    StringBuilder sbContext = new StringBuilder("");
                    if (null != BeanUtils.getProperty(row, (String) propertyEntry.getKey())) {
                        if ("投保单号".equals(propertyEntry.getValue()) || "保单号".equals(propertyEntry.getValue())) {
                            //避免读取时变换为科学记数 - 解决办法：加 \t(用Excel打开时，超过15位数字后会自动默认科学记数)
                            sbContext.append(BeanUtils.getProperty(row, (String) propertyEntry.getKey()) + "\t");
                        } else {
                            sbContext.append(BeanUtils.getProperty(row, (String) propertyEntry.getKey()));
                        }
                    }
                    csvFileOutputStream.write(sbContext.toString());
                    /*-------------------------------*/
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    csvFileOutputStream.newLine();
                }
            }
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void createFileTest() {
        List exportData = new ArrayList<Map>();
        Map row1 = new LinkedHashMap<String, String>();
        row1.put("1", "11");
        row1.put("2", "12");
        row1.put("3", "13");
        row1.put("4", "14");
        exportData.add(row1);
        row1 = new LinkedHashMap<String, String>();
        row1.put("1", "21");
        row1.put("2", "22");
        row1.put("3", "23");
        row1.put("4", "24");
        exportData.add(row1);
        LinkedHashMap map = new LinkedHashMap();
        map.put("1", "第一列");
        map.put("2", "第二列");
        map.put("3", "第三列");
        map.put("4", "第四列");

        String path = "d:/export";
        String fileName = "文件导出";
        File file = CSVUtil.createCSVFile(exportData, map, path, fileName);
        String fileNameNew = file.getName();
        String pathNew = file.getPath();
        System.out.println("文件名称：" + fileNameNew);
        System.out.println("文件路径：" + pathNew);
    }
}
