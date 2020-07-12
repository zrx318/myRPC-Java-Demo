package com.rongxin.provider.handler;

import com.rongxin.model.WmRequest;
import com.rongxin.provider.BusinessThreadRunnable;
import com.rongxin.util.NamedThreadFactory;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author com.rongxin
 * 服务端消息接收处理器
 */
@Sharable
public class NettyServerReceiveMessageHandler extends SimpleChannelInboundHandler<WmRequest> {

    private static final Logger logger = LoggerFactory.getLogger(NettyServerReceiveMessageHandler.class);
    protected static final ExecutorService SHARED_EXECUTOR = new ThreadPoolExecutor(200, 200, 0, TimeUnit.SECONDS, new SynchronousQueue<>(), new NamedThreadFactory("WM-RpcDemo-Business-POOL"));

    @Override
    public void channelRmeadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 客户端链路关闭在这里可以察觉的到
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //System.out.println("exceptionCaught! ");
        //cause.printStackTrace();
        //发生异常,关闭链路
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WmRequest request) throws Exception {
        System.out.println("received message ! method: " + request.getInvokedMethodName() + " port: " + request.getProviderService().getServerPort());
        if (ctx.channel().isWritable()) {
            SHARED_EXECUTOR.execute(new BusinessThreadRunnable(ctx, request));
        } else {
            logger.error("------------channel closed!---------------");
        }
    }
}
