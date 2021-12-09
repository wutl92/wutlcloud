package com.wutl.auth2center.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/8 21:01
 */
@RequestMapping("/order")
@Controller
public class OrderController {


    @GetMapping(value = "/getCurrentUser")
    @ResponseBody
    public Map<String, Object> getCurrentUser(Principal principal) {
        String name = principal.getName();
        Map<String, Object> map = new HashMap<>();
        map.put("ssss", "ssss");
        return map;
    }


    @GetMapping(value = "/getOrderInfo")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "getOrderInfoError")
    public Map<String, Object> getOrderInfo(String index) {
        Map<String, Object> map = new HashMap<>();
        int i = 1/0;
        return map;
    }


    public Map<String, Object> getOrderInfoError(String index) {
        //调用没数据，普通异常信息
        Map<String, Object> map = new HashMap<>();
        map.put("message", index);
        return map;
    }

}
