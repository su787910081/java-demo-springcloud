package com.suyh441.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 对Zuul 进行服务降级需要实现接口 FallbackProvider
 */
@Component
public class ConsumerFallback implements FallbackProvider {
    /**
     * 返回需要进行服务降级的微服务名称。
     * 只有这里返回的微服务名称被匹配才会执行下面的fallbackResponse() 方法
     */
    @Override
    public String getRoute() {
        // 对指定的微服务进行降级
        // return "abcmsc-consumer-depart-8080";
        // 指定对所有微服务进行降级
        // 这里可以使用通配符
        return "*";
    }

    /**
     * @param route 请求中的微服务名称，并且是匹配 getRoute() 返回值的微服务名称。
     *              它只会对指定的微服务进行服务降级。所以从这里就可以看出getRoute() 方法我们应该怎么实现。
     * @param cause
     * @return 一个接口，所以这里需要实现这个接口。若这里的返回值为null，则不进行降级。这个返回值将交给系统去调用。
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        // 若微服务不是abcmsc-consumer-depart-8080，则不进行降级
        if (!"abcmsc-consumer-depart-8080".equals(route)) {
            return null;
        }

        // 仅对abcmsc-consumer-depart-8080进行降级
        return new ClientHttpResponse() {

            /**
             *
             * @return 返回给前端的headers
             */
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

            /**
             *
             * @return 返回给前端的body 体
             * @throws IOException
             */
            @Override
            public InputStream getBody() throws IOException {
                String msg = "fallback:" + route;
                return new ByteArrayInputStream(msg.getBytes());
            }

            /**
             *
             * @return 返回给前端的状态码
             * @throws IOException
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            /**
             *
             * @return 返回给前端的状态码的整数值
             * @throws IOException
             */
            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.value();
            }

            /**
             *
             * @return 状态码的文本信息
             * @throws IOException
             */
            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
            }

            @Override
            public void close() {
                // 写资源释放代码
            }
        };
    }
}
