package com.thoughtvalley.poc.security.realms;

/**
 * Created by Naresh on 7/22/2014.
 */
import javax.annotation.PostConstruct;

import com.thoughtvalley.poc.security.models.AuthToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.springframework.stereotype.Component;

@Component
public class TokenRealm extends JdbcRealm {

    @PostConstruct
    public void overrideTokenClass() {
        setAuthenticationTokenClass(AuthToken.class);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token.getClass().isAssignableFrom(AuthToken.class);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        AuthToken authToken = (AuthToken) token;
        String username = (String) authToken.getPrincipal();
        if(authToken.isExpired())
            throw new AuthenticationException("Expired token.");

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, username, this.getName());
        return info;

    }

}
