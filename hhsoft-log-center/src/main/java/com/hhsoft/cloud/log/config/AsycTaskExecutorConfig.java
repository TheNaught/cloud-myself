package com.hhsoft.cloud.log.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @ClassName: AsycTaskExecutorConfig
 * @Description 线程池配置类、启用异步任务支持
 * @Author Jason Biao
 * @Date 2019/4/1 16:22
 * @Version 1.0
 *
 * 在做项目过程中，一些耗时长的任务可能需要在后台线程池中运行；典型的如发送邮件等，由于需要调用外部的接口来
 * 进行实际的发送操作，如果客户端在提交发送请求后一直等待服务器端发送成功后再返回，就会长时间的占用服务器的
 * 一个连接；当这类请求过多时，服务器连接数会不够用，新的连接请求可能无法得到满足，从而导致客户端连接失败。
 * 因此这类服务一般需要使用到后台线程池来处理。在这种情况下，我们可以直接使用concurrent包中的线程池来处理，
 * 也可以使用其它的方案如Quartz等组件中的线程池来解决；为适配这些不同的方案，
 * Spring引入了TaskExecutor接口作为顶层接口，并提供了几种不同的实现来满足不同的场景。
 *
 * @EnableAsync: 开启异步任务支持
 **/
@Configuration
@EnableAsync
public class AsycTaskExecutorConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //线程池大小
        taskExecutor.setCorePoolSize(50);
        //线程池最大线程数
        taskExecutor.setMaxPoolSize(100);

        return taskExecutor;
    }
}
