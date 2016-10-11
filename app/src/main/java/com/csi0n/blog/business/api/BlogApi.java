package com.csi0n.blog.business.api;

import com.csi0n.blog.business.pojo.request.GetHomeIndexRequest;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.core.net.NetWorkException;

/**
 * Created by csi0n on 10/10/16.
 */

public interface BlogApi {
    GetHomeIndexResponse GetHomeIndexResponse(GetHomeIndexRequest getHomeIndexRequest) throws NetWorkException;
}
