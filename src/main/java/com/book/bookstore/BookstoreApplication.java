package com.book.bookstore;

import com.book.bookstore.filters.JwtFilter;
import com.book.bookstore.services.impl.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@OpenAPIDefinition(info = @Info(title = "Books API", version = "1.0", description = "Bookstore Information"))
@SpringBootApplication(scanBasePackages = "com.book.bookstore")
public class BookstoreApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @EnableWebSecurity
    @Configuration
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private JwtFilter jwtFilter;

        @Value("${security.enable.csrf}")
        private boolean csrfEnabled;

        @Autowired
        private UserDetailsServiceImpl userDetailsService;

        @Override
        public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.cors().and()
                    .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/user").permitAll()
                    .antMatchers(HttpMethod.PUT).permitAll()
                    .antMatchers(HttpMethod.POST).permitAll()
                    .antMatchers(HttpMethod.DELETE).permitAll()
                    .anyRequest().authenticated().and().headers().contentSecurityPolicy("script-src ''self");

            if (csrfEnabled) {
                httpSecurity.csrf().disable();
            }
        }
    }
}
