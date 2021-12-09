package com.wutl.syscenter.demo;

import com.wutl.modelcenter.sys.user.SysUser;
import com.wutl.syscenter.demo.feign.FeignClientDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/10 9:58
 */
@RequestMapping("/demo")
@Controller
public class DemoController {
    @Autowired
    private FeignClientDemo feignClientDemo;

    @RequestMapping("/getUser")
    @ResponseBody
    public Map<String, Object> getUser(SysUser sysUser) {
        SysUser user = feignClientDemo.getUser(sysUser);
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return map;
    }


    @RequestMapping("/getMapInfo")
    @ResponseBody
    public Map<String, Object> getMapInfo(String code, String hello) {
        Map<String, Object> map = feignClientDemo.getMapInfo(code, hello);
        return map;
    }


    @RequestMapping("/getMapInfoByUri")
    @ResponseBody
    public Map<String, Object> getMapInfoByUri(String code, String hello) {
        Map<String, Object> map = null;
        try {
            URI uri = new URI("http://192.168.0.159:8080");
            map = feignClientDemo.getMapInfo(code, hello, uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/uploadFilePage")
    public String uploadFilePage() {
        return "upload_file";
    }


    /**
     * 实现文件上传
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file) {
        String hello = feignClientDemo.uploadFile(file, "hello");
        return hello;
    }

}
