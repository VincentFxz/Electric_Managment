package org.zju.electric_factory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.zju.electric_factory.entity.User;
import org.zju.electric_factory.service.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(value = "currentUserInterceptor")
public class CurrentUserInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
    private UserManager userManager;


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // Add the current user into the request
        User currentUser = userManager.getCurrentUser();
        if( currentUser != null ) {
            httpServletRequest.setAttribute( "currentUser", currentUser );
        }
    }
}

