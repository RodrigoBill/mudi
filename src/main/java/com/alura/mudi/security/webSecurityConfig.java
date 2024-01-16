package com.alura.mudi.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

 

@Configuration
@EnableWebSecurity
public class webSecurityConfig extends WebSecurityConfigurerAdapter{
    
    /* CONFIGURAÇÃO PARA AS REQUISIÇÕES DO PASSEM POR ESTE METODO ANTES  */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests()
                .antMatchers("/home/**", "/api/**")
                    .permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll())
                .logout(logout -> {
                    logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/home");
                })
                .csrf().disable();
    }

    /* BUSCAR USUARIO NO BANCO DE DADOS */
    @Autowired
    private DataSource dataSource;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

       /* UserDetails user = 
        User.builder()
        .username("gerente")
        .password(encoder.encode("gerente"))
        .roles("ADM")
        .build();*/

        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(encoder);
            /* .withUser(user);*/
    }

    /* CRIAR USUARIO IN MEMORY
    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        UserDetails user = 
            User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADM")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
     */

     

}
