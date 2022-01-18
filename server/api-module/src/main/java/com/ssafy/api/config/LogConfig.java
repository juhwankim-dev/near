package com.ssafy.api.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

@Slf4j
@Component
@Aspect
// 요청이 들어오면 로그 출력
public class LogConfig {
    @Pointcut("execution(* com.ssafy.api.controller.*.*(..))")  // 이런 패턴이 실행될 경우 수행
    public void loggerPointCut() {
    }

    @Around("loggerPointCut()")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable { // 2

    String params = getRequestParams(); // request 값 가져오기
    
    long startAt = System.currentTimeMillis();
    
    if(params != "") {
        String className = pjp.getSignature().getDeclaringTypeName();
        log.info("+++ REQUEST Param +++");
        log.info("+++ {}({}) => {}", className.substring(className.lastIndexOf(".") + 1), pjp.getSignature().getName(), params);
    }
    
    Object result = pjp.proceed(); // 4
    
    long endAt = System.currentTimeMillis();
    
        return result;
    }



  private String getBody(@RequestBody HttpServletRequest request) throws IOException {
     
      
      String body = null;
      StringBuilder stringBuilder = new StringBuilder();
      BufferedReader bufferedReader = null;

      InputStream inputStream = request.getInputStream();
      if (inputStream != null) {
          bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
          char[] charBuffer = new char[128];
          int bytesRead = -1;
          while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
              stringBuilder.append(charBuffer, 0, bytesRead);
          }
      } else {
          stringBuilder.append("");
      }

      body = stringBuilder.toString();
      return body;
  }

  // Get request values 
  private String getRequestParams() throws IOException {

    String params = "";
    StringJoiner sj = new StringJoiner(",");

    RequestAttributes requestAttributes = RequestContextHolder
        .getRequestAttributes(); // 3

    if (requestAttributes != null) {
      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
          .getRequestAttributes()).getRequest(); 

      Map<String, String[]> paramMap = request.getParameterMap();
      if (!paramMap.isEmpty()) {
          for (Entry<String, String[]> entry : paramMap.entrySet()) {
                String name = entry.getKey();
                String[] values = entry.getValue();
                sj.add(name + "=" + Arrays.toString(values));
          }
        params = sj.toString();
      }
    }

    return params;

  }
}