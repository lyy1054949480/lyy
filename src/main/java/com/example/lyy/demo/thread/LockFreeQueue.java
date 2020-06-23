package com.example.lyy.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class LockFreeQueue {
 
    private AtomicReferenceArray atomicReferenceArray;
    //代表为空，没有元素
    private static final  Integer EMPTY = null;
    //头指针,尾指针
    AtomicInteger head,tail;
 
 
    public LockFreeQueue(int size){
        atomicReferenceArray = new AtomicReferenceArray(new Integer[size + 1]);
        head = new AtomicInteger(0);
        tail = new AtomicInteger(0);
    }
 
    /**
     * 入队
     * @param element
     * @return
     */
    public boolean add(Integer element){
        int index = (tail.get() + 1) % atomicReferenceArray.length();
        if( index == head.get() % atomicReferenceArray.length()){
            System.out.println("当前队列已满,"+ element+"无法入队!");
            return false;
        }
        while(!atomicReferenceArray.compareAndSet(index,EMPTY,element)){
            return add(element);
        }
        tail.incrementAndGet(); //移动尾指针
        System.out.println("入队成功!" + element);
        return true;
    }
 
    /**
     * 出队
     * @return
     */
    public Integer poll(){
        if(head.get() == tail.get()){
            System.out.println("当前队列为空");
            return null;
        }
        int index = (head.get() + 1) % atomicReferenceArray.length();
        Integer ele = (Integer) atomicReferenceArray.get(index);
        if(ele == null){ //有可能其它线程也在出队
            return poll();
        }
        while(!atomicReferenceArray.compareAndSet(index,ele,EMPTY)){
            return poll();
        }
        head.incrementAndGet();
        System.out.println("出队成功!" + ele);
        return ele;
    }
 
    public void print(){
       StringBuffer buffer = new StringBuffer("[");
       for(int i = 0; i < atomicReferenceArray.length() ; i++){
           if(i == head.get() || atomicReferenceArray.get(i) == null){
               continue;
           }
           buffer.append(atomicReferenceArray.get(i) + ",");
       }
       buffer.deleteCharAt(buffer.length() - 1);
       buffer.append("]");
       System.out.println("队列内容:"    +buffer.toString());
 
    }
 
}