package com.wutl.syscenter.web;

import com.wutl.modelcenter.sys.user.SysUser;
import com.wutl.syscenter.common.Constants;
import com.wutl.syscenter.model.common.AuthorizationResponse;
import com.wutl.syscenter.service.user.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/8 10:14
 */
@RequestMapping
@Controller
public class LoginController {

    @Value("${auth2.baseUrl}")
    private String auth2BaseUrl;

    @Value("${auth2.clientId}")
    private String clientId;

    @Value("${auth2.clientSecret}")
    private String clientSecret;

    @Value("${auth2.scope}")
    private String scope;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    ISysUserService sysUserService;

    /**
     * 登录页面
     *
     * @param request:
     * @return: {@link String}
     * @Author: Wutl
     * @Date: 2021/11/8 10:15
     */
    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        List<SysUser> userList = sysUserService.getUserList();
        //当前系统登录成功之后的回调URL
        String redirectUrl = request.getParameter("redirectUrl");
        //当前系统请求认证服务器成功之后返回的Authorization Code
        String code = request.getParameter("code");
        //最后重定向的URL
        String resultUrl = "redirect:";
        HttpSession session = request.getSession();
        //当前请求路径
        String currentUrl = request.getRequestURL().toString();

        //code为空，则说明当前请求不是认证服务器的回调请求，则重定向URL到百度OAuth2.0登录
        if (StringUtils.isBlank(code)) {
            //如果存在回调URL，则将这个URL添加到session
            if (StringUtils.isNoneBlank(redirectUrl)) {
                session.setAttribute("redirectUrl", redirectUrl);
            }
            resultUrl += auth2BaseUrl + MessageFormat.format("/oauth/authorize?client_id={0}&response_type=code&scope={1}&display=popup&redirect_uri={2}"
                    , clientId, scope, currentUrl);
            System.out.println(redirectUrl);
        } else {
            //1. 通过Authorization Code获取Access Token
            AuthorizationResponse response = restTemplate.getForObject(auth2BaseUrl + "/oauth/token?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}"
                    , AuthorizationResponse.class
                    , clientId, clientSecret, code, currentUrl);

            //2. 如果正常返回
            if (response != null && StringUtils.isNoneBlank(response.getAccess_token())) {
                //2.1 将Access Token存到session
                session.setAttribute(Constants.SESSION_ACCESS_TOKEN, response.getAccess_token());
                //2.2 再次查询用户基础信息，并将用户ID存到session
                String baiduUser = restTemplate.getForObject(auth2BaseUrl + "/api/getCurrentUser?access_token={1}"
                        , String.class
                        , response.getAccess_token());
                /*if (baiduUser != null && StringUtils.isNoneBlank(baiduUser.getUserId())) {
                    System.out.println(baiduUser);

                    session.setAttribute(Constants.SESSION_USER_ID, baiduUser.getUserId());
                }*/
            }
            //3. 从session中获取回调URL，并返回
            redirectUrl = (String) session.getAttribute("redirectUrl");
            session.removeAttribute("redirectUrl");
            if (StringUtils.isNoneBlank(redirectUrl)) {
                resultUrl += redirectUrl;
            } else {
                resultUrl += "/index";
            }
        }
        return new ModelAndView(resultUrl);
    }
}
