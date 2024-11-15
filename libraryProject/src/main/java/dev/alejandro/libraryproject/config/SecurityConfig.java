package dev.alejandro.libraryproject.config;


import dev.alejandro.libraryproject.utils.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.jaas.JaasGrantedAuthority;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Principal;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//Su implementacion de seguridad con jwt, que es el proyecto final :)

@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http,AuthenticationManager manager) throws Exception{
    return http
            .authenticationManager(manager)
            .authenticationProvider(authenticationProvider())
            .csrf(Customizer.withDefaults())
            .sessionManagement(session -> {
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(Constants.Global.API_BASE_PATH
                + Constants.Global.API_VERSION + Constants.Libro.LIBRO_SERVICE_PATH+"/").hasAuthority("ROLE_ADMIN");
                auth.anyRequest().authenticated();
            })
            .httpBasic(Customizer.withDefaults())
            .build();
}
@Bean
AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
    return configuration.getAuthenticationManager();
}

@Bean
AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    daoAuthenticationProvider.setUserDetailsService(userDetailsService());
    return daoAuthenticationProvider;
}

@Bean
PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

@Bean
UserDetailsService userDetailsService(){
    return new InMemoryUserDetailsManager(
            User.withUsername("alejandro")
                    .password(passwordEncoder().encode("12345"))
                    .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                    .build()
    );
}

}
