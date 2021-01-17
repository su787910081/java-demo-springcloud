package com.suyh471.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这样的确可以处理限流时发生的异常
 * 但是不明白这个是什么原理，待有空的时候可以研究学习一下。
 */
@RestController
public class ExceptionHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error-code";
    }

    /**
     * TODO: 这里为什么是 /error
     *
     * @return
     */
    @RequestMapping(value="/error")
    public String error(){
        return "{\"result\":\"访问太多频繁，请稍后再访问！！！\"}";
    }
}
