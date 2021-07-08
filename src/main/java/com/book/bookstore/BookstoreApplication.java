package com.book.bookstore;

import com.book.bookstore.security.JWTAuthorizationFilter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.cors().and().csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/user").permitAll()
                    .antMatchers(HttpMethod.PUT).permitAll()
                    .antMatchers(HttpMethod.POST).permitAll()
                    .antMatchers(HttpMethod.DELETE).permitAll()
                    .anyRequest().authenticated().and().headers().contentSecurityPolicy("script-src ''self");
        }
    }
}
