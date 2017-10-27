package org.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther HuJiaqi
 * @createTime 2017年10月26日
 * @discription
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "hello word";
    }

}
