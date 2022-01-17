package web.security;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity//вкл безопасность
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private final SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    public SecurityConfig(UserDetailsService userDetailsService,
                          SuccessUserHandler successUserHandler) {
        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll() // при октрытии будет доступном всем пользователям
                .antMatchers("/users/**")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .and().formLogin()
                .successHandler(successUserHandler);// если аунтификация прошла успешно то выполняем преднастройку
    }
}
