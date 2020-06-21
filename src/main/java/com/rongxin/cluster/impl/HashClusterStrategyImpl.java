package com.rongxin.cluster.impl;

import com.rongxin.cluster.ClusterStrategy;
import com.rongxin.model.ProviderService;
import com.rongxin.util.IPHelper;

import java.util.List;

/**
 * @author com.rongxin
 */
public class HashClusterStrategyImpl implements ClusterStrategy {

    @Override
    public ProviderService select(List<ProviderService> providerServices) {
        //获取调用方ip
        String localIP = IPHelper.localIp();
        //获取源地址对应的hashcode
        int hashCode = localIP.hashCode();
        //获取服务列表大小
        int size = providerServices.size();
        return providerServices.get(hashCode % size);
    }
}
