package uzb.asadullaev.FirstSecurityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uzb.asadullaev.FirstSecurityApp.services.PersonDetailsService;



@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                // ...
                .csrf().disable()
                .authorizeHttpRequests(authorize -> {
                            try {
                                authorize
                                        .requestMatchers("/auth/login", "/auth/registration", "/error").permitAll()
                                        .anyRequest().authenticated()
                                        .and()
                                        .formLogin().loginPage("/auth/login")
                                        .loginProcessingUrl("/process_login")
                                        .defaultSuccessUrl("/hello", true)
                                        .failureUrl("/auth/login?error");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

        return http.build();
    }
//    protected void configure(HttpSecurity http) throws Exception {
//        // Конфигирируем сам Spring Security
//        // Конфигирируем авторизацию
//        http.authorizeHttpRequests()
//                .requestMatchers("/auth/login", "/error").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/auth/login")
//                .loginProcessingUrl("/process_login")
//                .defaultSuccessUrl("/hello", true)
//                .failureUrl("/auth/login?error");
//    }

    // Настраиваем аутентификацию
//    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(personDetailsService);
//    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


}
