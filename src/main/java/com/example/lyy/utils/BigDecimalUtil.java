package com.example.lyy.utils;

import java.math.BigDecimal;

/**
 * @title BigDecimalUtil
 * @description BigDecimal计算操作的工具类
 * @date 2019/6/13 10:26
 * @author yindq
 */
public class BigDecimalUtil {

    /**
     * 将 Integer 转化成非空的 BigDecimal
     *
     * @param integer
     * @return
     */
    public static Integer nonNullInteger(Integer integer){
        return integer == null ? 0 : integer;
    }

    /**
     * 将 Integer 转化成非空的 BigDecimal
     *
     * @param integer
     * @return
     */
    public static BigDecimal nonNullBigDecimal(Integer integer){
        return integer == null ? BigDecimal.ZERO : new BigDecimal(integer);
    }

    /**
     * 计算比例 (a/b) 保留四位小数
     *
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal computeRate(BigDecimal a,BigDecimal b){
        if(BigDecimal.ZERO.equals(b)){
            return BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        BigDecimal decimal = a.divide(b, 4, BigDecimal.ROUND_HALF_UP);
        BigDecimal multiply = decimal.multiply(new BigDecimal(100));
        return multiply.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算剩余比例 (1-a/b) 保留四位小数
     *
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal computeSurplusRate(BigDecimal a,BigDecimal b){
        if(BigDecimal.ZERO.equals(b)){
            return BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        BigDecimal decimal = BigDecimal.ONE.subtract(a.divide(b, 4, BigDecimal.ROUND_HALF_UP));
        BigDecimal multiply = decimal.multiply(new BigDecimal(100));
        return multiply.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
