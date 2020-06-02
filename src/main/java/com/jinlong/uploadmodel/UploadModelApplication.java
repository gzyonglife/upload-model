package com.jinlong.uploadmodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
public class UploadModelApplication {

    /**
     * 配置文件上传大小
     */
    @Bean
    public MultipartConfigElement getMultiConfig() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofGigabytes(3));
        factory.setMaxRequestSize(DataSize.ofGigabytes(3));
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(UploadModelApplication.class, args);
    }

}
