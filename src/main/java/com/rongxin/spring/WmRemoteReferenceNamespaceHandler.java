package com.rongxin.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author com.rongxin
 */
public class WmRemoteReferenceNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("reference", new InvokerFactoryBeanDefinitionParser());
    }
}
