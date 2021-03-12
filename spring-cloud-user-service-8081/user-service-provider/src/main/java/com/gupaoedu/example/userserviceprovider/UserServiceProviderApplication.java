package com.gupaoedu.example.userserviceprovider;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;



@SpringBootApplication
public class UserServiceProviderApplication {

    /**
     * springboot2.0版本需要添加 ServletRegistrationBean,
     * 因为springboot的默认路径不是 "/hystrix.stream"
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

    public static void main(String[] args) {

//        ConfigurableApplicationContext context=SpringApplication.run(UserServiceProviderApplication.class, args);
        SpringApplication springApplication=new SpringApplication(UserServiceProviderApplication.class);
        Map<String,Object> map=new HashMap<>();
        map.put("key","value");
        springApplication.setDefaultProperties(map);
        springApplication.run(args);
    }

}
