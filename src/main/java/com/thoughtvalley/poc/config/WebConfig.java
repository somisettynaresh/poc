package com.thoughtvalley.poc.config;

import com.springcryptoutils.core.cipher.Mode;
import com.springcryptoutils.core.cipher.symmetric.Cipherer;
import com.springcryptoutils.core.cipher.symmetric.CiphererImpl;
import com.springcryptoutils.core.signature.Signer;
import com.springcryptoutils.core.signature.SignerImpl;
import com.springcryptoutils.core.signature.Verifier;
import com.springcryptoutils.core.signature.VerifierImpl;
import com.thoughtvalley.poc.security.realms.TokenRealm;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naresh on 7/21/2014.
 */
@Configuration
@ComponentScan(basePackages={"com.thoughtvalley.poc"})
public class WebConfig {

    @Autowired
    public TokenRealm tokenRealm;

    @Value("classpath:aes-128-keystore.jck")
    private Resource keyStoreFile;

    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        List<Realm> shiroRealms = new ArrayList<Realm>();
        shiroRealms.add(jdbcRealm());
        securityManager.setRealms(shiroRealms);
        return securityManager;
    }

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/poc_users");
        dataSource.setUsername("root");
       // dataSource.setPassword("");
        return dataSource;
    }
    @Bean
    public JdbcRealm jdbcRealm(){
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource());
        return jdbcRealm;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        shiroFilter.setFilterChainDefinitions("/api/v1/security/login = anon\n" +
                "/api/v1/account/** = tokenFilter"
               );
        return shiroFilter;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(){
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(new Object[]{securityManager()});
        return methodInvokingFactoryBean;
    }

    @Bean
    @DependsOn(value="lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean(name="encrypter")
    public Cipherer encrypter(){
        CiphererImpl encrypter = new CiphererImpl();
        encrypter.setCipherAlgorithm("aes/CBC/PKCS5Padding");
        encrypter.setKeyAlgorithm("aes");
        encrypter.setMode(Mode.ENCRYPT);
        encrypter.setProvider("BC");
        return encrypter;
    }

    @Bean(name="decrypter")
    public Cipherer decrypter(){
        CiphererImpl decrypter = new CiphererImpl();
        decrypter.setCipherAlgorithm("aes/CBC/PKCS5Padding");
        decrypter.setKeyAlgorithm("aes");
        decrypter.setMode(Mode.DECRYPT);
        decrypter.setProvider("BC");
        return decrypter;
    }

    @Bean(name="keystore")
    public KeyStore keystore() throws NoSuchProviderException, KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore= KeyStore.getInstance("JCEKS");
        keyStore.load(keyStoreFile.getInputStream(), "mystorepass".toCharArray());
        return keyStore;
    }

    @Bean(name="publicKey")
    public PublicKey publicKey() throws IOException, KeyStoreException, NoSuchProviderException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = keystore();
        java.security.cert.Certificate certificate = keyStore.getCertificate("tokenSignature");
        return certificate.getPublicKey();
    }

    @Bean(name="privateKey")
    public PrivateKey privateKey() throws IOException, KeyStoreException, NoSuchProviderException, CertificateException, NoSuchAlgorithmException, UnrecoverableEntryException {
        KeyStore keyStore = keystore();
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("tokenSignature", new KeyStore.PasswordProtection("tokenSignatureKeyPass".toCharArray()));
        return privateKeyEntry.getPrivateKey();
    }

    @Bean(name="secretKey")
    public Key secretKey() throws IOException, KeyStoreException, NoSuchProviderException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore keyStore = keystore();
        Key secretKey = keyStore.getKey("testAESKey","testKeyPass".toCharArray());
        return secretKey;
    }

    @Bean(name="signer")
    public Signer signer() throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException, NoSuchProviderException, UnrecoverableEntryException {
        SignerImpl signer = new SignerImpl();
        signer.setAlgorithm("SHA256withRSA");
        signer.setProvider("BC");
        signer.setPrivateKey(privateKey());
        return signer;
    }

    @Bean(name="verifier")
    public Verifier verifier() throws NoSuchAlgorithmException, CertificateException, NoSuchProviderException, KeyStoreException, IOException {
        VerifierImpl verifier = new VerifierImpl();
        verifier.setPublicKey(publicKey());
        verifier.setProvider("BC");
        verifier.setAlgorithm("SHA256withRSA");
        return verifier;
    }

    @PostConstruct
    public void init(){
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }


}
