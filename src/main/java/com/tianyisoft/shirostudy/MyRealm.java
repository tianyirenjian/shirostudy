package com.tianyisoft.shirostudy;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm implements Realm {
    @Override
    public String getName() {
        return "MyRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String name = authenticationToken.getPrincipal().toString();
        if (name.startsWith("hell")) {
            return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), authenticationToken.getCredentials(), this.getName());
        }
        throw new AuthenticationException("login failed.");
    }
}
