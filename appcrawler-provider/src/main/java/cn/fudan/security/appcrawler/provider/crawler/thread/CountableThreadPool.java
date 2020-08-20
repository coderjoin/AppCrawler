package cn.fudan.security.appcrawler.provider.crawler.thread;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qiaoying
 * @date 2018/11/15 19:14
 */
public class CountableThreadPool {

    private int threadNum;

    private AtomicInteger threadAlive = new AtomicInteger();

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition condition = reentrantLock.newCondition();

    private ExecutorService executorService;

    public CountableThreadPool(int threadNum){
        this.threadNum = threadNum;
        this.executorService = Executors.newFixedThreadPool(threadNum);
    }

    public CountableThreadPool(int threadNum, ExecutorService executorService){
        this.threadNum = threadNum;
        this.executorService = executorService;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public int getThreadAlive() {
        return threadAlive.get();
    }

    public void setThreadAlive(AtomicInteger threadAlive) {
        this.threadAlive = threadAlive;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void execute(final Runnable runnable){

        if (threadAlive.get() >= threadNum){
            try {
                reentrantLock.lock();
                while (threadAlive.get() >= threadNum){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                reentrantLock.unlock();
            }

        }
        threadAlive.incrementAndGet();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } finally {
                    try {
                        reentrantLock.lock();
                        threadAlive.decrementAndGet();
                        condition.signal();
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            }
        });
    }

    public boolean isShutdown(){
        return executorService.isShutdown();
    }

    public void shutdown(){
        executorService.shutdown();
    }
}

