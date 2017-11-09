package com.imooc.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by 邓仁波 on 2017-11-2.
 * 重写登录验证
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    //注册账号的时候用 passwordEncoder.encode() 加密
    //验证的时候 loadUserByUsername password直接放入加密前的 字符即可 不用自己解密
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名: " + username);
        //可以注入jdbc redis等 获取用户详细信息
        //根据查找到的用户信息判断账号是否被冻结

        //$2a$10$2pnnzjVevYW0A.DAD2tfUO.RVzesPqEwmc/IJC896x9kqeqd.jNsS 为你数据库密码
        return new User(username, "$2a$10$2pnnzjVevYW0A.DAD2tfUO.RVzesPqEwmc/IJC896x9kqeqd.jNsS"
                //账号可用，账号没过期,凭证没过期,账号没被锁定
                , true, true, true, true
                , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
