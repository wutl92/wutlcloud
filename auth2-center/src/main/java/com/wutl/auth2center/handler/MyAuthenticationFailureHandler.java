package com.wutl.auth2center.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/6 14:27
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String url = request.getContextPath() + "/login?error=" + URLEncoder.encode(exception.getMessage(),"UTF-8");
        response.sendRedirect(url);
    }
}
