package com.wutl.auth2center.config;

import com.wutl.auth2center.config.service.MyUserDetailsService;
import com.wutl.auth2center.cons.AuthCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.sql.DataSource;


/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/6 12:40
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
        /*//配置两个客户端,一个用于password认证一个用于client认证
        String secret = new BCryptPasswordEncoder().encode("123456");////对密码进行加密
        clients.inMemory().withClient("client_1")
                .resourceIds(AuthCons.DEMO_RESOURCE_ID)
                .authorizedGrantTypes("client_credentials", "authorization_code", "refresh_token", "password")
                .redirectUris("http://www.baidu.com", "http://192.168.0.127:8082/login", "http://127.0.0.1:8082/login")
                .scopes("select")
                .authorities("client")
                .secret(secret)
                .and().withClient("client_2")
                .resourceIds(AuthCons.DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("client")
                .secret(secret);*/
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(new InMemoryTokenStore())
//                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService).
                allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        endpoints.pathMapping("/oauth/confirm_access", "/custom/confirm_access");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //允许表单认证
        oauthServer.tokenKeyAccess("permitAll()") //(1)
                .checkTokenAccess("permitAll()")//(2)
                .allowFormAuthenticationForClients();//(3)
    }
}
