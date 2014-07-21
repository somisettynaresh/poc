package com.thoughtvalley.poc.controllers;

import com.thoughtvalley.poc.constants.UserRestURIConstants;
import com.thoughtvalley.poc.models.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.google.common.collect.ImmutableMap.of;

/**
 * Created by Naresh on 7/21/2014.
 */
@Controller
@RequestMapping("/user/authenticate")
public class AuthenticationController {

    @RequestMapping(value = UserRestURIConstants.LOGIN_USER, method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity login(@RequestBody User user) {
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getUsername(), user.getPassword(), false));
            return new ResponseEntity(of("authenticated",SecurityUtils.getSubject().isAuthenticated()),HttpStatus.OK);
        }
        catch(AuthenticationException ex){
            System.out.println(ex.getStackTrace());
            return new ResponseEntity(of("error", "Invalid Username or Password"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = UserRestURIConstants.LOGOUT, method = RequestMethod.POST)
    public @ResponseBody ResponseEntity logout() {
      SecurityUtils.getSubject().logout();
        return new ResponseEntity(HttpStatus.OK);
    }

}
