package com.thoughtvalley.poc.security.models;

/**
 * Created by Naresh on 7/22/2014.
 */
import com.thoughtvalley.poc.security.token.Token;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("prototype")
public class AuthToken implements AuthenticationToken, Token {

    private static final long serialVersionUID = 7376782454935451752L;

    private String username;
    private long expireTimestamp;
    private String ip;
    private String userAgent;

    @Override
    public Object getPrincipal() {
        return this.username;
    }

    @Override
    public Object getCredentials() {
        return username;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expireTimestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getExpireTimestamp() {
        return expireTimestamp;
    }

    public void setExpireTimestamp(long expireTimestamp) {
        this.expireTimestamp = expireTimestamp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
