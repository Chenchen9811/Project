package com.project.revolvingcabinet;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 解决springboot中http传输时出现的某个习惯性异常
 *
 * @author hcy
 */
@Configuration
public class PortalTomcatWebServerCustomizer {

    /**
     * 解决非法字符的问题
     *
     * solutions for Error parsing HTTP request header Note: further occurrences of
     * HTTP request parsing errors will be logged at DEBUG level. 解决异常的原因:
     * 1、服务端提供http接口，客户端访问的时候加了https 2、设置tomcat的 server.xml 中的maxHttpHeaderSize的值
     * 3、更改tomcat的版本（
     */
    @Bean
    public TomcatServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((Connector connector) -> {
            connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
            connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}");
        });
        return factory;
    }
}