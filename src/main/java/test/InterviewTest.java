package test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InterviewTest {

    public static class CachedData {
        private Object data;
        private volatile boolean cacheFlag = false;
        private final ReadWriteLock lock = new ReentrantReadWriteLock();
        /**
         * read lock
         */
        private final Lock rLock = lock.readLock();
        /**
         * write lock
         */
        private final Lock wLock = lock.writeLock();

        public void processCachedData() {
            rLock.lock();
            if (!cacheFlag) {
                // 释放读锁
                rLock.unlock();
                // 获取写锁
                wLock.lock();
                try {
                    if (!cacheFlag) {
                        data = this.loadData();
                        cacheFlag = true;
                    }
                    // 释放写锁前，降低为读锁
                    rLock.lock();
                } finally {
                    wLock.unlock();
                }

            }
            // 这里依旧持有的是读锁
            try {
                // do some thing
                handleData(data);
            } finally {
                rLock.unlock();
            }
        }

        private Object loadData() {
            return "data";
        }

        private void handleData(Object data) {
            System.out.println("handle data" + data);
        }
    }

}