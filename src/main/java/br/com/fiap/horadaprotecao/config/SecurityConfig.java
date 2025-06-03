package br.com.fiap.horadaprotecao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/login", "/register", "/register/**").permitAll()


                        .requestMatchers("/actuator/health").permitAll()


                        .requestMatchers("/actuator/**").hasRole("ADMIN")


                        .requestMatchers("/home", "/home/**").hasAnyRole("ADMIN", "USER")


                        .requestMatchers("/vendedor/novo", "/vendedor/salvar", "/vendedor/editar/**", "/vendedor/deletar/**").hasRole("ADMIN")
                        .requestMatchers("/vendedor/**").hasAnyRole("ADMIN", "USER")

                        
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)  // Redireciona para /home após login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password(encoder.encode("admin"))
                        .roles("ADMIN")
                        .build(),
                User.withUsername("fernando@email.com")
                        .password(encoder.encode("fernando"))
                        .roles("USER")
                        .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
