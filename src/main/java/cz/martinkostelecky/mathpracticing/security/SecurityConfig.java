package cz.martinkostelecky.mathpracticing.security;

import cz.martinkostelecky.mathpracticing.exception.UnauthorizedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Martin Kostelecký
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UnauthorizedHandler unauthorizedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                //.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        //httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(unauthorizedHandler))
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/login", "/authenticate", "/", "/addition",
                                        "/result", "/subtraction", "/css/**", "/img/**", "/audio/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())

                .sessionManagement(sessionManagement ->
                        sessionManagement.invalidSessionUrl("/login?expired")
                                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                                .maximumSessions(2)
                                .maxSessionsPreventsLogin(true))
                .formLogin(login -> login.loginPage("/login"))
                .logout(logout -> logout.deleteCookies("JSESSIONID"));

        return httpSecurity.build();
    }

    //add BCrypt encoded password
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password("$2a$12$mPAadeBGoLVtgCBWbh83JupXX1c.cGwNbqxi.5sGfqlfCe4c.USmi")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
        return builder.build();
    }

}
