package com.tenpo.math.audit;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Scanner;

public class AuditInterceptor implements HandlerInterceptor {

    private final IAuditService auditService;

    public AuditInterceptor(IAuditService auditService){
        this.auditService = auditService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
            Audit audit = Audit.builder()
                    .auditTime(LocalDateTime.now())
                    .httpMethod(request.getMethod())
                    .loggedUser(getUser(request))
                    .body(parseBody(request))
                    .requestURI(request.getRequestURI())
                    .build();
            auditService.save(audit);
    }

    private String getUser(HttpServletRequest request){
        return request.getUserPrincipal()!=null? request.getUserPrincipal().getName():"";
    }


    private String parseBody(HttpServletRequest request) throws Exception{
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter(",");
            StringBuilder sb = new StringBuilder();
            String st;
            while (s.hasNext()){
                st=s.next();
                sb.append(st.contains("password")?"\n    \"password\":********":st);
            }
            return sb.toString();
        }
        return "";
    }
}
