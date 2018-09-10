/**
 *
 */
package com.kezuo.device;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author lee
 */
public class SyncFuture<T> implements Future<T> {


    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();
    /**
     * 需要响应线程设置的响应结果。
     */
    private T response;

    public SyncFuture() {
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        try{
            lock.lockInterruptibly();
            condition.await();
            return this.response;
        }finally {
            lock.unlock();
        }


    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        try {
            lock.lockInterruptibly();
            if(condition.await(timeout, unit)){
                return this.response;
            }
            return null;
        }finally {
            lock.unlock();
        }

    }

    @Override
    public boolean isCancelled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isDone() {
        if (response != null) {
            return true;
        }
        return false;
    }


    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        // TODO Auto-generated method stub
        return false;
    }

    // 用于设置响应结果，并且做countDown操作，通知请求线程
    public void setResponse(T response) throws InterruptedException {
        try{
            lock.lockInterruptibly();
            this.response = response;
            condition.signal();
        }finally {
            lock.unlock();
        }
    }


}
