package com.alura.mudi.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.Getter;
import lombok.Setter;

public class InterceptorDeAcessos extends HandlerInterceptorAdapter{

    public static List<Acesso> acessos = new ArrayList<Acesso>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
                Acesso acesso = new Acesso();
                acesso.path = request.getRequestURI();
                acesso.data = LocalDateTime.now();

                request.setAttribute("acesso", acesso);

                return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
                
                Acesso acesso = (Acesso)request.getAttribute("acesso");
                acesso.duracao = Duration.between(acesso.data, LocalDateTime.now());

                acessos.add(acesso);
    }

    @Getter
    @Setter
    public class Acesso {

        private String path;
        private LocalDateTime data;
        private Duration duracao;    
        
    }
    
}
