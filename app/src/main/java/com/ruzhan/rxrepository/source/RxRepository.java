package com.ruzhan.rxrepository.source;

/**
 * create ruzhan: 2018/6/21 10:08
 */
public final class RxRepository {

    private static RxRepository INSTANCE;

    private RxRepository() {
        //no instance
    }

    public static RxRepository get() {
        if (INSTANCE == null) {
            synchronized (RxRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RxRepository();
                }
            }
        }
        return INSTANCE;
    }


}
