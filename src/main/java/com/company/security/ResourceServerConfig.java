package com.company.security;

import com.company.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Autowired
    private AppConfig app;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(app.getResourceId())
                .tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .and()
                .authorizeRequests().antMatchers("/api/add/user").hasAnyAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/api/users/id").hasAnyAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/tasks").hasAnyAuthority("TEACHER")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.PUT, "/api/tasks/id").hasAnyAuthority("TEACHER")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/tasks").hasAnyAuthority("STUDENT", "TEACHER")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/tasks/finish/id").hasAnyAuthority("STUDENT")
                .and()
                .authorizeRequests().antMatchers("/actuator/**").permitAll().anyRequest().hasAnyAuthority("ADMIN");

    }
}
