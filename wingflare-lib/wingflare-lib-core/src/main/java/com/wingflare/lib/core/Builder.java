package com.wingflare.lib.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author naizui_ycx
 * @date {2023/03/06}
 * @description 通用对象构造类
 */
public class Builder<T> {

    private final Supplier<T> instantiator;

    private final List<Consumer<T>> modifiers = new ArrayList<>();

    public Builder(Supplier<T> instant) {
        this.instantiator = instant;
    }

    public static <T> Builder<T> of(Supplier<T> instant) {
        return new Builder<>(instant);
    }

    public <P1> Builder<T> with(Consumer1<T, P1> consumer, P1 p1) {
        Consumer<T> c = instance -> consumer.accept(instance, p1);
        modifiers.add(c);
        return this;
    }

    public <P1, P2> Builder<T> with(Consumer2<T, P1, P2> consumer, P1 p1, P2 p2) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3> Builder<T> with(Consumer3<T, P1, P2, P3> consumer, P1 p1, P2 p2, P3 p3) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3, P4> Builder<T> with(Consumer4<T, P1, P2, P3, P4> consumer, P1 p1, P2 p2, P3 p3, P4 p4) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4);
        modifiers.add(c);
        return this;
    }

    public <P1, P2, P3, P4, P5> Builder<T> with(Consumer5<T, P1, P2, P3, P4, P5> consumer, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4, p5);
        modifiers.add(c);
        return this;
    }

    public <P1> Builder<T> with(boolean expression, Consumer1<T, P1> consumer, P1 p1) {
        if (expression) {
            return with(consumer, p1);
        }
        return this;
    }

    public <P1, P2> Builder<T> with(boolean expression, Consumer2<T, P1, P2> consumer, P1 p1, P2 p2) {
        if (expression) {
            return with(consumer, p1, p2);
        }
        return this;
    }

    public <P1, P2, P3> Builder<T> with(boolean expression, Consumer3<T, P1, P2, P3> consumer, P1 p1, P2 p2, P3 p3) {
        if (expression) {
            return with(consumer, p1, p2, p3);
        }
        return this;
    }

    public <P1, P2, P3, P4> Builder<T> with(boolean expression, Consumer4<T, P1, P2, P3, P4> consumer, P1 p1, P2 p2, P3 p3, P4 p4) {
        if (expression) {
            return with(consumer, p1, p2, p3, p4);
        }
        return this;
    }

    public <P1, P2, P3, P4, P5> Builder<T> with(boolean expression, Consumer5<T, P1, P2, P3, P4, P5> consumer, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
        if (expression) {
            return with(consumer, p1, p2, p3, p4, p5);
        }
        return this;
    }

    public T build() {
        T value = instantiator.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }


    /**
     * 1 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer1<T, P1> {
        /**
         * 接收参数方法
         *
         * @param t  对象
         * @param p1 参数二
         */
        void accept(T t, P1 p1);
    }

    /**
     * 2 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer2<T, P1, P2> {
        /**
         * 接收参数方法
         *
         * @param t  对象
         * @param p1 参数一
         * @param p2 参数二
         */
        void accept(T t, P1 p1, P2 p2);
    }

    /**
     * 3 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer3<T, P1, P2, P3> {
        /**
         * 接收参数方法
         *
         * @param t  对象
         * @param p1 参数一
         * @param p2 参数二
         * @param p3 参数三
         */
        void accept(T t, P1 p1, P2 p2, P3 p3);
    }

    @FunctionalInterface
    public interface Consumer4<T, P1, P2, P3, P4> {
        /**
         * 接收参数方法
         *
         * @param t  对象
         * @param p1 参数一
         * @param p2 参数二
         * @param p3 参数三
         * @param p4 参数四
         */
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4);
    }

    @FunctionalInterface
    public interface Consumer5<T, P1, P2, P3, P4, P5> {
        /**
         * 接收参数方法
         *
         * @param t  对象
         * @param p1 参数一
         * @param p2 参数二
         * @param p3 参数三
         * @param p4 参数四
         * @param p5 参数五
         */
        void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }

}
