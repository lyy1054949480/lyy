package com.example.lyy.util.clone;

import java.io.*;

/**
 * @ProjectName: JavaUtilsProject
 * @Package: com.sl.utils.deepCopy
 * @ClassName: MyUtil
 * @Author: zc1997
 * @Description: 深拷贝工具类
 * @Date: 2020/2/14 11:50
 * @Version: 1.0
 */
public class DeepCloneUtil {

    private DeepCloneUtil() {
        throw new AssertionError();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();

        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }
}