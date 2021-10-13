package zn.zyh.back_code.utils.countutil;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
public class CountUtil {
    //线程数量
    private static int threadTotal=200;

    private static AtomicInteger count= new AtomicInteger(0);

    private static Semaphore semaphore = new Semaphore(threadTotal);

    private static ExecutorService exec =  Executors.newCachedThreadPool();

    public static void add(){
        exec.execute(()->{
            try{
                semaphore.acquire();
                count.incrementAndGet();
                semaphore.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

}
