//package com.cai.auth.service;
//
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
///**
// * Created by Fant.J.
// */
//@Component
//public class MyUserDetailsService implements UserDetailsService {
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String passwd = "123";
//        System.out.println("收到的账号"+username);
//
//        System.out.println("查到的密码"+passwd);
//        return new User(username, passwd, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
//    }
//}
