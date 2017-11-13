package com.imooc.security.browser;

import com.imooc.security.browser.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Created by 邓仁波 on 2017-11-2.
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
    @Autowired
    private ImoocAuthenticationFailureHandler imoocAuthenticationFailureHandler;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true); 配置该属性第一次启动会建表 第二次启动要屏蔽 因为表已经存在 会报错
        return tokenRepository;
    }

    //登录密码加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        //BCryptPasswordEncoder 为Security实现的
        //也可以用自己的加密 方法实现PasswordEncoder接口 实现加密方法和 比较方法即可
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
        //表单登录
        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")//设置自己的登录路径
                .loginProcessingUrl("/authentication/form")//表单登录提交路径
                .successHandler(imoocAuthenticationSuccessHandler)//登录成功进行自己的操作
                .failureHandler(imoocAuthenticationFailureHandler)//登录失败进行自己的操作
                .and()
                .rememberMe().tokenRepository(persistentTokenRepository())//配置记住我的数据源
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())//配置记住我的过期时间
                .userDetailsService(userDetailsService)//设置登录逻辑
//        //弹窗口登录
//        http.httpBasic()
                .and()
                //指定下面的都是授权的配置
                .authorizeRequests()
                //访问/imooc-signIn.html 放行 不需要身份认证
                .antMatchers("/authentication/require", securityProperties.getBrowser().getLoginPage()
                        , "/code/image").permitAll()
                //任何请求
                .anyRequest()
                //都需要身份认证
                .authenticated()
                .and().csrf().disable();
        super.configure(http);
    }
}
