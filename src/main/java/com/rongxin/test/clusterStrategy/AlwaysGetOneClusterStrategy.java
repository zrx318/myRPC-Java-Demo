package com.rongxin.test.clusterStrategy;

import com.rongxin.cluster.ClusterStrategy;
import com.rongxin.model.ProviderService;

import java.util.List;

/**
 * @author com.rongxin
 */
public class AlwaysGetOneClusterStrategy implements ClusterStrategy {

    @Override
    public ProviderService select(List<ProviderService> providerServices) {
        System.out.println("AlwaysGetOneClusterStrategy");
        return providerServices.get(0);
    }
}
