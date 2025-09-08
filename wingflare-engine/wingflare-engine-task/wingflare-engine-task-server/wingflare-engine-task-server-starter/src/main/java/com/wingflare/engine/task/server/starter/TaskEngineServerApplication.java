package com.wingflare.engine.task.server.starter;

import com.wingflare.engine.task.server.common.rpc.server.grpc.GrpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class TaskEngineServerApplication {

    private final static Logger log = LoggerFactory.getLogger(TaskEngineServerApplication.class);

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(TaskEngineServerApplication.class, args);
    }

    @Bean
    public ApplicationRunner nettyStartupChecker(GrpcServer grpcServer,
                                                 ServletWebServerFactory serverFactory) {
        return args -> {
            // 判定Grpc
            boolean started =  grpcServer.isStarted();
            // 最长自旋10秒，保证 grpcHttpServer启动完成
            int waitCount = 0;
            while (!started && waitCount < 100) {
                log.info("--------> wingflare-task server is staring....");
                TimeUnit.MILLISECONDS.sleep(100);
                waitCount++;
                started = grpcServer.isStarted();
            }

            if (!started) {
                log.error("--------> wingflare-task server startup failure.");
                // Netty启动失败，停止Web服务和Spring Boot应用程序
                serverFactory.getWebServer().stop();
                SpringApplication.exit(SpringApplication.run(TaskEngineServerApplication.class));
            }
        };
    }
}
