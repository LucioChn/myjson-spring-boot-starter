package com.nobody.myjson.config;

import com.nobody.myjson.service.MyJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 自动装配类
 * @Author Mr.nobody
 * @Date 2021/2/27
 * @Version 1.0
 */
@Configuration // 标识此类是配置类
@ConditionalOnClass(MyJsonService.class) // 表示只有指定的class在classpath上时才能被注册
@EnableConfigurationProperties(MyJsonProperties.class) // 激活@ConfigurationProperties
public class MyJsonConfiguration {

    private MyJsonProperties myJsonProperties;

    // 自动注入配置类
    @Autowired
    public MyJsonConfiguration(MyJsonProperties myJsonProperties) {
        this.myJsonProperties = myJsonProperties;
    }

    // 创建MyJsonService对象，注入到Spring容器中
    @Bean
    @ConditionalOnMissingBean(MyJsonService.class)
    public MyJsonService myJsonService() {
        MyJsonService myJsonService = new MyJsonService();
        myJsonService.setPrefixName(myJsonProperties.getPrefixName());
        myJsonService.setSuffixName(myJsonProperties.getSuffixName());
        return myJsonService;
    }


}
