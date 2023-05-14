package com.asan.bank.aspects;


import com.asan.bank.exceptionhandler.exceptions.ServiceException;
import com.asan.bank.security.AuthService;
import com.asan.bank.security.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.PrintWriter;
import java.io.StringWriter;

@Aspect
@Component
@Slf4j
public class ControllersAspect {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;


    @Before("within(com.asan.bank.controllers.AbstractController+ )")
    public void before() throws ServiceException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            log.info("request coming from " + request.getServerName() + " AND iP is => " + request.getLocalAddr() + " AND CALL URL =>  " + request.getRequestURI());
        }
    }


    @Around("within(com.asan.bank.controllers.AbstractController+ )")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        LogModel logModel = new LogModel();
        logModel.setMethodName(joinPoint.getSignature().getName());
        logModel.setRequest(joinPoint.getArgs());


        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

//            if (  (request.getHeader("jwt") == null) || !authService.isTokenValid(request.getHeader("jwt"))) {
//
//              throw new ServiceException("Authorization_Failed_Login_Again");
//            }


            if ( (request.getHeader("jwt") == null) || !jwtService.validUser(request.getHeader("jwt") )) {

                throw new ServiceException("Authorization_Failed_Login_Again");
            }
        }


        Object value;
        try {
            value = joinPoint.proceed();
            if (value != null) {
                logModel.setResponse(value);
                return value;
            }
            log.info("Success req " + objectMapper.writeValueAsString(logModel));
        } catch (Throwable e) {


            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            e.printStackTrace(printWriter);
            writer.close();
            printWriter.close();
            logModel.setErrorTrace(writer.toString());

            log.error("Failure req " + objectMapper.writeValueAsString(logModel));
            throw e;

        }


        return value;
    }


}
