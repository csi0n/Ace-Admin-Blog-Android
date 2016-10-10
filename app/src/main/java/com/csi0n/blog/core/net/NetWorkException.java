package com.csi0n.blog.core.net;

/**
 * Created by chqss on 2016/4/29 0029.
 */
public class NetWorkException extends Throwable {
    private Throwable detailThrowable;

    public NetWorkException(Throwable throwable) {
        super(throwable);
        this.detailThrowable = throwable;
    }

    public Throwable getDetailThrowable() {
        return detailThrowable;
    }

    @Override
    public String toString() {
        return "NetWorkException{" +
                "detailThrowable=" + detailThrowable +
                '}';
    }
}
