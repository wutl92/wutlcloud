package com.wutl.syscenter.demo.feign;

import com.wutl.modelcenter.sys.user.SysUser;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Map;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/10 9:47
 */
@FeignClient(name = "feignClientDemo", url = "http://192.168.0.127:8083")
public interface FeignClientDemo {

    @RequestMapping(method = RequestMethod.POST, value = "/demo/getUser")
    SysUser getUser(SysUser sysUser);

    /**
     * fegin默认参数放在requestBody里面，放到参数里面要加requestParam中
     *
     * @param code:
     * @param hello:
     * @return: {@link Map< String, Object>}
     * @Author: Wutl
     * @Date: 2021/11/10 10:16
     */
    @RequestMapping(method = RequestMethod.POST, value = "/demo/getMapInfo")
    Map<String, Object> getMapInfo(String code, @RequestParam String hello);


    /**
     * 第三方业务接口
     * 加了URI 则会请求URI的地址，不再请求注解上的地址
     *
     * @return 结果
     */
    @RequestMapping(method = RequestMethod.GET, value = "/demo/getMapInfo")
    Map<String, Object> getMapInfo(String code, @RequestParam String hello, URI newHost);


    @RequestMapping(value = {"/demo/fileUpload"},
            method = {RequestMethod.POST},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    String uploadFile(
            @RequestPart(value = "fileName") MultipartFile fileName,
            @RequestParam(value = "bucketName", required = false) String bucketName);

}
