package com.alkemy.ong.auth.config;

import com.alkemy.ong.auth.filter.JwtRequestFilter;
import com.alkemy.ong.auth.service.impl.UserDetailsCustomService;
import com.alkemy.ong.enums.RolName;
import com.alkemy.ong.domain.util.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsCustomService userDetailsCustomServiceService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsCustomServiceService).passwordEncoder(this.passwordEncoder());
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
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/auth/login", "/auth/register","/api-docs/**","/swagger-ui*/*").permitAll()
                .antMatchers("/auth/users", "/auth/users/*", "/api/assets/upload").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers("/categories/page","/news/page").hasAnyAuthority(RolName.ROLE_USER.toString())
                .antMatchers(HttpMethod.POST, "/slides").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/slides").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/slides/{id}").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/slides/{id}").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/testimonials").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/testimonials").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/news/{id}").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/news/{id}").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/news/{id}").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/categories").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/categories/{id}").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/categories/{categoryId}").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/activities/{id}").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/testimonials/{id}").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/activities").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/categories/{id}").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/organization/public").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/organization/public").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/organization/public/{organizationId}").hasAnyAuthority(RolName.ROLE_USER.toString())
                .antMatchers(HttpMethod.GET, "/comments").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, Url.MEMBERS_URI + "/{id}").hasAnyAuthority(RolName.ROLE_ADMIN.toString())
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
