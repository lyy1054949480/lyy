package com.example.lyy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;
 
/**
 * Created by Administrator on 2017/2/8.
 */
 
public class CPUUtils {
    private static final double CPU_TEMP_ERROR = -1000000;
    //cpu核数
    private static int sLastCpuCoreCount = -1;
    private static String cpuTempFilePath = null;
 
    /***
     * 计算CPU核数
     * @return CPU核数或者可用的CPU核数，以及Runtime.getRuntime().availableProcessors();
     */
    public static int calcCpuCoreCount() {
 
        if (sLastCpuCoreCount >= 1) {
            // 已经读取过有缓存了
            return sLastCpuCoreCount;
        }
 
        try {
            // 获取包含CPU信息的目录
            final File dir = new File("/sys/devices/system/cpu/");
            // 过滤
            final File[] files = dir.listFiles(new FileFilter() {
 
                public boolean accept(File pathname) {
                    //匹配
                    if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                        return true;
                    }
                    return false;
                }
            });
 
            // 获得cpu核数
            sLastCpuCoreCount = files.length;
 
        } catch(Exception e) {
            sLastCpuCoreCount = Runtime.getRuntime().availableProcessors();
        }
 
        return sLastCpuCoreCount;
    }
    /***
     * 取得最大CPU时钟
     * @return 0表示错误
     */
    public static int takeMaxCpuFreq(int id) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("/sys/devices/system/cpu/cpu").append(id).append("/cpufreq/cpuinfo_max_freq");
        return readIntegerFile(stringBuffer.toString());
    }
 
    /***
     * 取得现在CPU时钟
     * @return 0表示错误
     */
    public static int takeCurrentCpuFreq(int id) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("/sys/devices/system/cpu/cpu").append(id).append("/cpufreq/scaling_cur_freq");
        return readIntegerFile(stringBuffer.toString());
    }
 
    /***
     * 取得最小CPU时钟
     * @return 0表示错误
     */
    public static int takeMinCpuFreq(int id) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("/sys/devices/system/cpu/cpu").append(id).append("/cpufreq/cpuinfo_min_freq");
        return readIntegerFile(stringBuffer.toString());
    }
 
    public static int[] takeAllCpuFreq(){
        int count = calcCpuCoreCount();
        if( count >= 1 ){
            int[] result = new int[count];
            for(int i=0;i<result.length;i++){
                result[i] = takeCurrentCpuFreq(i);
            }
            return result;
        }
       return null;
    }
 
    /***
     * 读取文件
     * @param filePath:文件目录
     * @return
     */
    private static int readIntegerFile(String filePath) {
        try {
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath)), 1000);
            final String line = reader.readLine();
            reader.close();
            return Integer.parseInt(line);
        } catch (Exception e) {
 
            return 0;
        }
    }
    private static double readTempFile(String filePath) throws FileNotFoundException {
        try {
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath)), 1000);
            final String line = reader.readLine();
            reader.close();
            return Double.parseDouble(line);
        }catch (FileNotFoundException ex){
            throw ex;
        } catch (Exception e) {
            return CPU_TEMP_ERROR;
        }
    }

 
    /***
     * 格式化cpu频率
     * @param clockHz cpu频率
     * @return "XX MHz" 或者 "X.X GHz"
     */
    public static String formatFreq(int clockHz) {
        //MHz
        if (clockHz < 1000*1000) {
            return (clockHz / 1000) + "MHz";
        }
 
        // a.b GHz
        final int a = (clockHz / 1000 / 1000);
        final int b = (clockHz / 1000 / 100) % 10;
        return a + "." + b + "GHz";
    }
   //存放温度文件的地址，不同CPU不同
    public static final String[] CPU_TEMP_FILE_PATHS = new String[]{
            "/sys/devices/system/cpu/cpu0/cpufreq/cpu_temp"
            , "/sys/devices/system/cpu/cpu0/cpufreq/FakeShmoo_cpu_temp"
            , "/sys/class/thermal/thermal_zone0/temp"
            , "/sys/class/i2c-adapter/i2c-4/4-004c/temperature"
            , "/sys/devices/platform/tegra-i2c.3/i2c-4/4-004c/temperature"
            , "/sys/devices/platform/omap/omap_temp_sensor.0/temperature"
            , "/sys/devices/platform/tegra_tmon/temp1_input"
            , "/sys/kernel/debug/tegra_thermal/temp_tj"
            , "/sys/devices/platform/s5p-tmu/temperature"
            , "/sys/class/thermal/thermal_zone1/temp"
            , "/sys/class/hwmon/hwmon0/device/temp1_input"
            , "/sys/devices/virtual/thermal/thermal_zone1/temp"
            , "/sys/devices/platform/s5p-tmu/curr_temp"
            , "/sys/devices/virtual/thermal/thermal_zone0/temp"
            , "/sys/class/thermal/thermal_zone3/temp"
            , "/sys/class/thermal/thermal_zone4/temp"
    };
 
 
    public static double takeCPUTemp(){
        double temp = CPU_TEMP_ERROR;
 
        if( cpuTempFilePath != null ){
            try {
                temp = readTempFile(cpuTempFilePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if( checkTempValue(temp) ){
                return temp;
            }else if( checkTempValue(temp/1000L) ){
                return temp/1000L;
            }
        }
 
        for (String path:CPU_TEMP_FILE_PATHS){
            try {
                temp = readTempFile(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                continue;
            }
            if( checkTempValue(temp) ){
                cpuTempFilePath = path;
                break;
            }else if( checkTempValue(temp/1000L) ){
                cpuTempFilePath = path;
                break;
            }
            temp = CPU_TEMP_ERROR;
 
        }
        return temp;
    }
 
    private static boolean checkTempValue(double temp) {
        if( temp == CPU_TEMP_ERROR ) return false;
        if( temp < -30 ) return false;
        if( temp > 250 ) return false;
        return true;
    }
 
 
}
