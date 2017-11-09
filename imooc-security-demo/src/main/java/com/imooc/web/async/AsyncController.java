package com.imooc.web.async;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * Created by 邓仁波 on 2017-11-1.
 */
@RestController
public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeferredResultHolder deferredResultHolder;
    @Autowired
    private MockQueue mockQueue;

    //Callable方式异步返回请求
    @RequestMapping("/order")
    public Callable<String> order() throws InterruptedException {
        logger.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程返回");
                return "success";
            }
        };
        logger.info("主线程返回");
        return result;
    }

    //DeferredResult方式异步返回请求
    @RequestMapping("/order1")
    public DeferredResult<String> order1() throws InterruptedException {
        logger.info("主线程开始");
        String orderNum= RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNum);
        DeferredResult<String>result=new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNum,result);
        logger.info("主线程返回");
        return result;
    }
}
