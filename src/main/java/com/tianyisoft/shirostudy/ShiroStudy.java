package com.tianyisoft.shirostudy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroStudy {
    public static void main(String[] args) {
        ShiroStudy study = new ShiroStudy();
        // study.iniLogin();
        study.myRealmLogin();
    }

    private void iniLogin() {
        System.out.println("-----------------------ini login-------------------------");
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("tianyi", "123456");
        System.out.println(token.toString());

        try {
            subject.login(token);
        } catch (AuthenticationException exception) {
            System.out.println(exception.getMessage());
            System.out.println("login failed.");
        }
        System.out.println(subject.isAuthenticated());
        if (subject.isAuthenticated()) {
            System.out.println("logout");
            subject.logout();
        }
    }

    private void myRealmLogin() {
        System.out.println("---------------------myRealm login-----------------------");
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-myrealm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("hello", "world");
        System.out.println(token.toString());

        try {
            subject.login(token);
        } catch (AuthenticationException exception) {
            System.out.println(exception.getMessage());
            System.out.println("login failed.");
        }
        System.out.println(subject.isAuthenticated());
        if (subject.isAuthenticated()) {
            System.out.println("logout");
            subject.logout();
        }
    }
}
