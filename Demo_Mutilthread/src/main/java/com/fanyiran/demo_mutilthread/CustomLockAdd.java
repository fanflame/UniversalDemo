package com.fanyiran.demo_mutilthread;

import android.support.annotation.NonNull;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by fanqiang on 2019/4/11.
 */
public class CustomLockAdd implements Add {
    private MyLock myLock = new MyLock();
    private int i = 0;
    @Override
    public void increase() {
        myLock.lock();
        i++;
        myLock.unlock();
    }

    @Override
    public int getResult() {
        return i;
    }

    class MyLock implements Lock {
        private AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();
        private LinkedBlockingDeque<Thread> blockingDeque = new LinkedBlockingDeque<>();
        @Override
        public void lock() {
            while (!threadAtomicReference.compareAndSet(null, Thread.currentThread())) {
                blockingDeque.add(Thread.currentThread());
                LockSupport.park();
                blockingDeque.remove(Thread.currentThread());
            }
        }

        @Override
        public void unlock() {
            threadAtomicReference.compareAndSet(Thread.currentThread(),null);
            for (Thread thread : blockingDeque) {
                LockSupport.unpark(thread);
            }
        }
        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, @NonNull TimeUnit unit) throws InterruptedException {
            return false;
        }


        @NonNull
        @Override
        public Condition newCondition() {
            return null;
        }
    }

}
