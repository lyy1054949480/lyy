package com.example.lyy.util;


import java.io.*;

/**
 * @描述： 字节、对象互转工具
 * @作者： lux
 * @创建日期： 2018-6-13 11:48
 * @版权： 江泰保险经纪股份有限公司
 */
public class Object2ByteUtil {

    /**
     * 对象转byte
     * @param obj
     * @return
     */
    public static byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * byte转对象
     * @param bytes
     * @return
     */
    public static Object ByteToObject(byte[] bytes) {
        Object obj = new Object();
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        try {
            // bytearray to object
            ObjectInputStream oi = new ObjectInputStream(bi);
            obj =  (Object)oi.readObject();
            bi.close();
            oi.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }




}
