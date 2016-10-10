package com.csi0n.blog.business.api.retrofit;


import com.csi0n.blog.core.net.NetWorkException;

/**
 * RetrofitAdapter <br/>
 * Created by csi0n on 2016-01-10.
 */
public abstract class RetrofitAdapter<T> {

    abstract T call() throws Exception;

    protected T get() throws NetWorkException {
        T t;
        try {
            t = call();
        } catch (Exception e) {
            throw new NetWorkException(e);
        }
        return t;
    }
}