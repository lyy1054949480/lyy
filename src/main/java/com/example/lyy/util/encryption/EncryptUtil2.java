package com.example.lyy.util.encryption;


public class EncryptUtil2 {

    public static EncryptUtil2 me;

    private EncryptUtil2(){
        //单例

    }
    //双重锁
    public static EncryptUtil2 getInstance(){
        if (me==null) {
            synchronized (EncryptUtil2.class) {
                if(me == null){
                    me = new EncryptUtil2();
                }
            }
        }
        return me;
    }


}