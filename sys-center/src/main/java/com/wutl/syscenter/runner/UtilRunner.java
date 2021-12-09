package com.wutl.syscenter.runner;

import com.wutl.syscenter.utils.redis.RedisUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/12 14:06
 */
@Component
public class UtilRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("===========开始初始化RedisUtil=============");
        RedisUtil.initRedisTemplate();
        System.out.println("===========初始化RedisUtil完成=============");
    }
}
