package com.kincaps.www.config;

import com.kincaps.www.service.CustomPersistentTokenRepository;
import com.kincaps.www.service.CustomUserDetailsService;
import com.kincaps.www.service.UsuarioService;
import com.kincaps.www.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CustomPersistentTokenRepository persistentTokenRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/login", "/register", "/terminos", "/politica",
                                "/style/**", "/scripts/**", "/img/**"
                        ).permitAll()
                        .requestMatchers("/mantenimiento/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .successHandler((request, response, authentication) -> {
                            String email = authentication.getName();
                            Usuario usuario = usuarioService.findByEmail(email);
                            request.getSession().setAttribute("usuario", usuario);

                            boolean isAdmin = authentication.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                            if (isAdmin) {
                                response.sendRedirect(request.getContextPath() + "/mantenimiento");
                            } else {
                                response.sendRedirect(request.getContextPath() + "/gorras");
                            }
                        })
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .deleteCookies("remember-me")
                        .permitAll()
                )
                .rememberMe(remember -> remember
                        .tokenRepository(persistentTokenRepository)
                        .tokenValiditySeconds(86400 * 30) // 30 días
                        .userDetailsService(customUserDetailsService)
                );
        return http.build();
    }
}