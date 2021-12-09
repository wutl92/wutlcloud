package com.wutl.usercenter.demo;

import com.wutl.modelcenter.sys.user.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/10 9:53
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(value = "/getMapInfo")
    @ResponseBody
    public Map<String, Object> getMapInfo(HttpServletRequest request, HttpServletResponse response
            , String code, String hello) {
        System.out.println(code);
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("hello", hello);
        String id = request.getSession().getId();
        System.out.println(id);
        return result;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public SysUser getUser(@RequestBody SysUser sysUser) {
        return sysUser;
    }

    @RequestMapping("/uploadFilePage")
    public String uploadFilePage(Model model) {
        SysUser sysUser = new SysUser();
        sysUser.setId("123456");
        sysUser.setUsername("hello");
        sysUser.setPassword("!@#$");
        sysUser.setEmail("123@qq.com");
        model.addAttribute("user", sysUser);
        return "upload_file";
    }

    @RequestMapping("/uploadMultiPage")
    public String uploadMultiPage() {
        return "upload_multi";
    }

    /**
     * 实现文件上传
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file) {
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "F:/test";
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    /**
     * 实现多文件上传
     * <p>
     * public @ResponseBody String multifileUpload(@RequestParam("fileName")List<MultipartFile> files)
     */
    @RequestMapping(value = "multifileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String multifileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
        if (files.isEmpty()) {
            return "false";
        }
        String path = "F:/test";
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);
            if (file.isEmpty()) {
                return "false";
            } else {
                File dest = new File(path + "/" + fileName);
                //判断文件父目录是否存在
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        return "true";
    }

}
