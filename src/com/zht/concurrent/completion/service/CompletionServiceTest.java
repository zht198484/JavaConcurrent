package com.zht.concurrent.completion.service;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zht198484 on 2017/6/27.
 * <p>
 * CompletionService main api demo
 * First Complete, First Return
 */
public class CompletionServiceTest{
    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();
        CompletionService cs = new ExecutorCompletionService(es);

        cs.submit(() -> {
            Thread.sleep(5000);
            return "a complete after 5000 ms";
        });

        cs.submit(() -> {
            Thread.sleep(1000);
            return "b complete after 1000 ms";
        });

        es.shutdown();

        while (!es.isTerminated()){
            System.out.println(cs.take().get());
        }

        /*System.out.println(cs.poll());
        System.out.println(cs.take().get());
        System.out.println(cs.take().get());
        System.out.println(cs.poll());*/

    }
}
