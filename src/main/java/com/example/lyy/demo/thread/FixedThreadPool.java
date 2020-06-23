package com.example.lyy.demo.thread;

/**
 * 创建一个固定线程数的线程池，在任何时候最多只有nThreads个线程被创建。如果在所有线程都处于活动状态时，有其他任务提交，他们将等待队列中直到线程可用。如果任何线程由于执行过程中的故障而终止，将会有一个新线程将取代这个线程执行后续任务。
 *
 */
public class FixedThreadPool {
}
