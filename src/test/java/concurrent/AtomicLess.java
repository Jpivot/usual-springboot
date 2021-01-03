package concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Sherlock on 2020/2/6.
 */
public class AtomicLess {
    private static final int MAX_THREADS=3;//线程数
    private static final int TASK_COUNT=3;//任务数
    private static final int TARGET_COUNT=10000000;//目标总数

    private AtomicLong acount = new AtomicLong(0L);
    private long count=0;

    static CountDownLatch cdlsync=new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdlatomic=new CountDownLatch(TASK_COUNT);

    protected synchronized long inc(){
        return ++count;
    }

    protected synchronized long getCount(){
        return count;
    }

    public void clearCount(){
        count=0;
    }

    public class SyncThread implements Runnable{
        protected String name;
        protected long starttime;
        AtomicLess out;
        public SyncThread(AtomicLess o,long starttime,String name){
            out=o;
            this.starttime=starttime;
            this.name=name;
        }
        @Override
        public void run(){
            long v=out.getCount();
            while (v<TARGET_COUNT){
                v=out.inc();
            }
            long endtime=System.currentTimeMillis();
            System.out.println("SyncThread "+this.name+" spend: "+(endtime-starttime)+"ms"+" v="+v);
            cdlsync.countDown();
        }
    }

    public void testSync() throws InterruptedException{
        ExecutorService exe=Executors.newFixedThreadPool(MAX_THREADS);
        long starttime=System.currentTimeMillis();
        SyncThread syncThread=new SyncThread(this,starttime,String.valueOf(2));
        for (int i=0;i<TASK_COUNT;i++){

            //这里提交了同一个Runable类型的任务
            exe.submit(syncThread);
        }
        cdlsync.await();
        exe.shutdown();
    }

    public static class MyThread extends Thread{
        @Override
        public void run(){
            for(int i=1;i<=10;i++){
                System.out.println(i);
            }
        }

    }

    public class AtomicThread implements Runnable{
        protected String name;
        protected long starttime;
        public AtomicThread(long starttime){
            this.starttime=starttime;
        }
        @Override
        public void run(){
            long v=acount.get();
            while (v<TARGET_COUNT){
                v=acount.incrementAndGet();
            }
            long endtime=System.currentTimeMillis();
            System.out.println("AtomicThread spend: "+(endtime-starttime)+"ms"+" v="+v);
            cdlatomic.countDown();
        }
    }

    public void testAtomic() throws InterruptedException{
        ExecutorService exe=Executors.newFixedThreadPool(MAX_THREADS);
        long starttime=System.currentTimeMillis();
//        AtomicThread atomic=new AtomicThread(starttime);
        for (int i=0;i<TASK_COUNT;i++){
            AtomicThread atomic=new AtomicThread(starttime);
            exe.submit(atomic);
        }
        cdlatomic.await();
        exe.shutdown();
    }

    public static void main(String args[]) throws InterruptedException{
//        AtomicLess atomicLess=new AtomicLess();
//        atomicLess.testSync();
//        atomicLess.testAtomic();
        MyThread myThread=new MyThread();
        myThread.start();
        System.out.println("245235566");

    }
}
