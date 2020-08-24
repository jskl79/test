package com.solo.test.config;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class dataSourceInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String clientId = request.getHeader("clientId");

        // /SomeContextPath/publisher
//        String clientId

//        // /SomeContextPath/advertiser
//        String prefixAdvertiser = contextPath + "/advertiser";
//
//        // /SomeContextPath/publisher/dashboard
//        // /SomeContextPath/advertiser/dashboard
//
//        String uri = request.getRequestURI();
//        System.out.println("URI:"+ uri);
//
//        if(uri.startsWith(prefixPublisher)) {
//            request.setAttribute("keyDS", "PUBLISHER_DS");
//        }
//
//        else if(uri.startsWith(prefixAdvertiser)) {
//            request.setAttribute("keyDS", "ADVERTISER_DS");
//        }

        return true;
    }

}
