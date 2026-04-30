package com.msvc.invex.entrypoints.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Collectors;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        request.setAttribute(START_TIME, System.currentTimeMillis());

        String headers = extractHeaders(request);

        log.info("REQUEST -> method={} uri={} headers={}",
                request.getMethod(),
                request.getRequestURI(),
                headers
        );

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        long startTime = (long) request.getAttribute(START_TIME);
        long duration = System.currentTimeMillis() - startTime;

        log.info("RESPONSE -> method={} uri={} status={} duration={}ms",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                duration
        );

        if (ex != null) {
            log.error("ERROR -> {}", ex.getMessage());
        }
    }

    private String extractHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();

        return Collections.list(headerNames)
                .stream()
                .map(name -> name + "=" + request.getHeader(name))
                .collect(Collectors.joining(", "));
    }
}