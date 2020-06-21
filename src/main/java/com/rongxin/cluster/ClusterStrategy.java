package com.rongxin.cluster;

import com.rongxin.model.ProviderService;

import java.util.List;

/**
 * @author com.rongxin
 */
public interface ClusterStrategy {

    /**
     * 负载策略算法
     *
     * @param providerServices
     * @return
     */
    ProviderService select(List<ProviderService> providerServices);
}
