package com.ruzhan.rxrepository.source;

import io.reactivex.functions.Consumer;

/**
 * Created by ruzhan123 on 2018/6/21.
 */
public class EmptyConsumer implements Consumer<Object> {

    public static EmptyConsumer create() {
        return new EmptyConsumer();
    }

    @Override
    public void accept(Object o) throws Exception {

    }
}
