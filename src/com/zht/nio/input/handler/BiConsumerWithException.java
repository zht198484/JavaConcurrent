package com.zht.nio.input.handler;

/**
 * Created by zht198484 on 2017/8/27.
 * BiConsumer which throws exception
 */
@FunctionalInterface
public interface BiConsumerWithException<T, U> {
    void accept(T t, U u) throws Exception;
}
