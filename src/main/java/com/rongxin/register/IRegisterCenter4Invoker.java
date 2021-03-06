package com.rongxin.register;

import com.rongxin.model.InvokerService;
import com.rongxin.model.ProviderService;

import java.util.List;
import java.util.Map;

/**
 * 消费端注册中心接口
 * @author com.rongxin
 */
public interface IRegisterCenter4Invoker {

    /**
     * 消费端初始化服务提供者信息本地缓存
     *
     * @param remoteAppKey
     * @param groupName
     */
    void initProviderMap(String remoteAppKey, String groupName);


    /**
     * 消费端获取服务提供者信息
     *
     * @return
     */
    Map<String, List<ProviderService>> getServiceMetaDataMap4Consume();


    /**
     * 消费端将消费者信息注册到zk对应的节点下
     *
     * @param invoker
     */
    void registerInvoker(InvokerService invoker);

    /**
     * 判断消费者注册中心是否已经初始化过
     * @return
     */
    boolean isInit();
}
