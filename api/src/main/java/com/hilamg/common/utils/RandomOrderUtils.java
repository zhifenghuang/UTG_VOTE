package com.hilamg.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class RandomOrderUtils {

    /**
     * 转账单号
     *
     * @return
     */
    public static String generateSimpleSerialno() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        sb.append(sd.format(now));
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, 1);
        sb.append(RandomStringUtils.randomNumeric(4));
        return sb.toString();
    }

    public static void main(String[] args) {


//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 15, 3L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5));
//        for (int i = 0; i < 15; i++) {
//            Task task = new Task(i);
//            threadPoolExecutor.execute(task);
//            System.out.println("线程池中线程数目：" + threadPoolExecutor.getPoolSize() + "，队列中等待执行的任务数目：" +
//                    threadPoolExecutor.getQueue().size() + "，已执行玩别的任务数目：" + threadPoolExecutor.getCompletedTaskCount());
//        }
//        threadPoolExecutor.shutdown();
        Set<Short> stack = new HashSet<>();
        Short before = -1;
        for (Short i = 0; i < 100; i++) {
            stack.remove(before);
            before = i;
            stack.add(i);
        }
        System.out.println(stack.size());
    }

    public static class Task implements Runnable {

        private int num;

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行任务  " + num);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + num + "执行完毕");
        }
    }
}

