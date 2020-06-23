package com.example.lyy.demo.thread;

/**
 * 工作窃取线程池
 *
 * 假设共有三个线程同时执行, A, B, C
 * 当A,B线程池尚未处理任务结束,而C已经处理完毕,则C线程会从A或者B中窃取任务执行,这就叫工作窃取
 * 假如A线程中的队列里面分配了5个任务，而B线程的队列中分配了1个任务，当B线程执行完任务后，它会主动的去A线程中窃取其他的任务进行执行
 * WorkStealingPool 背后是使用 ForkJoinPool实现的
 */
public class WorkStealingPool {
}
