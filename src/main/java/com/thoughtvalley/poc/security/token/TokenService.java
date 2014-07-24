package com.thoughtvalley.poc.security.token;

import org.springframework.stereotype.Component;

/**
 * This interface defines method signatures to encrypt and decrypt 
 * a token.
 * @author richard
 */
@Component
public interface TokenService {
	public <T extends Token> String encrypt(T token);
	public <T extends Token> T decrypt(String encryptedToken);
}
