package com.example.lyy.util.invoke;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 */
public class ReflectionMethodUtil {
    /**
     *单个对象通过类方法反射取值
     * @param methodName 方法体
     * @param className 类路径
     * @param parameterTypes 参数类型：{String.class,Int.class}
     * @param objs 参数
     * @return
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static String getSingleObject(String methodName, String className, Class<?>[] parameterTypes, Object[] objs) throws ClassNotFoundException, SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //里面写自己的类名及路径
        Class<?> c = Class.forName(className);
        Object obj = c.newInstance();
        //第一个参数写的是方法名,第二个\第三个\...写的是方法参数列表中参数的类型
        Method method=c.getMethod(methodName, parameterTypes);
        //invoke是执行该方法,并携带参数值
        String str2= (String) method.invoke(obj, objs);
        return str2;
    }

    /**
     * list对象通过反射取值
     * @param methodName 方法名
     * @param className  类路径
     * @param parameterTypes 参数类型：{String.class,Int.class}
     * @param objs 参数
     * @return
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static List getListObject(String methodName, String className, Class<?>[] parameterTypes, Object[] objs) throws ClassNotFoundException, SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //里面写自己的类名及路径
        Class<?> c = Class.forName(className);
        Object obj = c.newInstance();
        //第一个参数写的是方法名,第二个\第三个\...写的是方法参数列表中参数的类型
        Method method=c.getMethod(methodName, parameterTypes);
        //invoke是执行该方法,并携带参数值
       List str2= (List) method.invoke(obj, objs);
        return str2;
    }

    /**
     * 利用反射, 往对象的指定名字的field中设置String类型的值
     * @param object 指定对象
     * @param fieldName field名称
     * @param filedValue String类型的值
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object setFieldValue(Object object, String fieldName, String filedValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, filedValue);
        return object;
    }

    /**
     * 利用反射, 获取对象中的某个field的值.
     * @param object 对象
     * @param fieldName field名称
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
}
