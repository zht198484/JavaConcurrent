package com.zht.concurrent.stamped.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by zht198484 on 2017/6/26.
 * stamped lock demo
 */
public class StampedLockTest {
    private String a;
    private String b;
    private StampedLock stampedLock = new StampedLock();
    public void write(String a, String b){
        long stamp = stampedLock.writeLock();
        try {
            this.a = a;
            this.b = b;
        }finally {
            stampedLock.unlockWrite(stamp);
        }
    }
    public String read(){
        long stamp = stampedLock.tryOptimisticRead();
        String a1 = a;
        String b1 = b;
        if(!stampedLock.validate(stamp)){
            long rstamp = stampedLock.readLock();
            try{
                a1 = a;
                b1 = b;
            }finally {
                stampedLock.unlock(rstamp);
            }
        }
        return a1+b1;
    }
}
