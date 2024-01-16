package com.alura.mudi.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.mudi.interceptor.InterceptorDeAcessos;
import com.alura.mudi.interceptor.InterceptorDeAcessos.Acesso;

@RequestMapping("/api")
@RestController
public class AcessosRest {


    @GetMapping("/acessos")
    public List<Acesso> getAcessos(){
        return InterceptorDeAcessos.acessos;
    }
    
}
