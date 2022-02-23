package com.cai.order.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (User)表控制层
 *
 * @author clz
 * @since 2022-01-28 14:44:26
 */
@RestController
@RequestMapping("")
@RefreshScope
public class TestController {

    @Value("${returnString: hello}")
    private String returnString;

    @GetMapping("/test")
    public String test() {
        return returnString;
    }
}

