package com.paraondevou.paraondevou.security

import com.paraondevou.paraondevou.entity.Usuario
import com.paraondevou.paraondevou.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UsuarioRepository usuarioRepository

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/imagem/").permitAll()
                .anyRequest().authenticated()
                .and()

        // filtra requisições de login
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)

        // filtra outras requisições para verificar a presença do JWT no header
                .addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        List<Usuario> it = usuarioRepository.findAllByAtivo(true)

        Iterator<Usuario> listUsuario = it.iterator()
        while (listUsuario.hasNext()) {
            Usuario usuario = listUsuario.next()
            auth.inMemoryAuthentication()
                    .withUser(usuario.email)
                    .password("{noop}" + usuario.senha)
                    .roles("ADMIN")
        }
        /*
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}password")
                .roles("ADMIN");
        /*
        Iterator it = usuarioRepository.findByAtivo(true)

        while (it.hasNext()) {
            Usuario usuario = it.next()
            auth.inMemoryAuthentication()
                    .withUser(usuario.email)
                    .password("{noop}" + usuario.senha)
                    .roles("ADMIN")
        }*/
    }
}