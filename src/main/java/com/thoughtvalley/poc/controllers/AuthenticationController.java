package com.thoughtvalley.poc.controllers;

import com.thoughtvalley.poc.constants.UserRestURIConstants;
import com.thoughtvalley.poc.models.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.google.common.collect.ImmutableMap.of;

/**
 * Created by Naresh on 7/21/2014.
 */
@Controller
@RequestMapping("/user/authenticate")
public class AuthenticationController {

    @RequestMapping(value = "/login/{username}", method = RequestMethod.GET)
    public @ResponseBody  ResponseEntity login(@PathVariable String username) {
        try {
            System.out.println(username);
            SecurityUtils.getSubject().login(new UsernamePasswordToken("admin", "password", false));
            return new ResponseEntity(of("authenticated",SecurityUtils.getSubject().isAuthenticated()),HttpStatus.OK);
        }
        catch(AuthenticationException ex){
           ex.printStackTrace();
            return new ResponseEntity(of("error", "Invalid Username or Password"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = UserRestURIConstants.LOGOUT, method = RequestMethod.GET)
    public @ResponseBody ResponseEntity logout() {
      SecurityUtils.getSubject().logout();
        return new ResponseEntity(HttpStatus.OK);
    }

}
