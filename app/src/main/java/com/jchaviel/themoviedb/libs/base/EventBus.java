package com.jchaviel.themoviedb.libs.base;

/**
 * Created by jchaviel on 6/6/2017.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
