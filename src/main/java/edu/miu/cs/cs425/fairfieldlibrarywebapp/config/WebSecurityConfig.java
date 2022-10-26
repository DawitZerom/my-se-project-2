package edu.miu.cs.cs425.fairfieldlibrarywebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.miu.cs.cs425.fairfieldlibrarywebapp.service.impl.LibraryUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(LibraryUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // .csrf().disable()
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/resources/static/**", "/images/**", "/css/**", "/library/public/**").permitAll()
                .antMatchers("/", "/library").permitAll()
                // .antMatchers("/library/secured/admin/**").hasRole("ADMIN")
                // .antMatchers("/library/secured/registrar/**").hasRole("REGISTRAR")
                // .antMatchers("/library/secured/student/**").hasRole("STUDENT")

                .antMatchers("/library/secured/book/**").permitAll()
                .antMatchers("/library/secured/checkin/**").permitAll()
                .antMatchers("/library/secured/checkoutrecord/**").permitAll()
                .antMatchers("/library/secured/librarymember/**").permitAll()
                .antMatchers("/library/secured/membertype/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/library/public/login")
                .defaultSuccessUrl("/library/secured/home")
                .failureUrl("/library/public/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/library/public/logout"))
                .logoutSuccessUrl("/library/public/login?logout")
                .permitAll()
                .and()
                .exceptionHandling();
    }

}
