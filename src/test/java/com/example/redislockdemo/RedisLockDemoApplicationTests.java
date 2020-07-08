package com.example.redislockdemo;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SpringBootTest
class RedisLockDemoApplicationTests {

    @Test
    void contextLoads()  {
        // 1.构造redisson实现分布式锁必要的Config
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:5379").setPassword("123456").setDatabase(0);
// 2.构造RedissonClient
        RedissonClient redissonClient = Redisson.create(config);
// 3.获取锁对象实例（无法保证是按线程的顺序获取到）
        RLock rLock = redissonClient.getLock("my_lock");
        try {
            /**
             * 4.尝试获取锁
             * waitTimeout 尝试获取锁的最大等待时间，超过这个值，则认为获取锁失败
             * leaseTime   锁的持有时间,超过这个时间锁会自动失效（值应设置为大于业务处理的时间，确保在锁有效期内业务能处理完）
             */
            boolean res = rLock.tryLock(8000, 5000,TimeUnit.SECONDS);
            if (res) {
                //成功获得锁，在这里处理业务
            }
        } catch (Exception e) {
            throw new RuntimeException("aquire lock fail");
        }finally{
            //无论如何, 最后都要解锁
            rLock.unlock();
        }
    }

    @Test
    public  void   bbb(){
        /*ReentrantLock lock=new ReentrantLock();
        lock.lock();

        ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
        CountDownLatch countDownLatch=new CountDownLatch(5);*/
        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();
        concurrentHashMap.put("hhhh","88888");
        MyRedisLock myRedisLock=new MyRedisLock();
        MyRedisLock myRedisLock1=new MyRedisLock();
        MyRedisLock[] myRedisLocks=new MyRedisLock[]{myRedisLock1,myRedisLock1};
        System.out.println(myRedisLocks);




    }


    @Test
    public void  sixh(){

        hhh();
    }

    private void hhh(){
        hhh();
    }


    @Test
    public void  uu(){
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url);
        }

        System.out.println("------------------------------");
        URL[] urls1 = ((URLClassLoader) ClassLoader.getSystemClassLoader().getParent()).getURLs();
        for (URL url : urls1) {
            System.out.println(url);
        }

        System.out.println("------------------------------");
        URL[] urls2 = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
        for (URL url : urls2) {
            System.out.println(url);
        }
    }

}
