package com.datang.datangcarmanager.api;

/**
 * @name DaTangCarManager
 * @class name：com.datang.datangcarmanager.api
 * @class describe
 * @anthor toby
 * @time 2016/12/2 上午8:56
 * @change
 * @chang time
 * @class describe
 */
public class ApiFactory {

    protected static final Object monitor = new Object();
    static CarApi carApiSingleton = null;


    //return Singleton
    public static CarApi getCarApiSingleton() {
        synchronized (monitor) {
            if (carApiSingleton == null) {
                carApiSingleton = new ApiRetrofit().getCarApiService();
            }
            return carApiSingleton;
        }
    }

}
