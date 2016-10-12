package com.csi0n.blog.app;

import com.csi0n.blog.business.api.BlogApi;
import com.csi0n.blog.business.api.retrofit.BlogApiRetrofitImpl;
import com.csi0n.blog.business.domain.BlogDomain;
import com.csi0n.blog.business.domain.impl.BlogDomainImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Created by csi0n on 10/10/16.
 */
public class BindImplModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BlogDomain.class).to(BlogDomainImpl.class);
        bind(BlogApi.class).to(BlogApiRetrofitImpl.class);
    }
}
