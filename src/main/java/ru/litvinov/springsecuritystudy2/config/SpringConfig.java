package ru.litvinov.springsecuritystudy2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.litvinov.springsecuritystudy2.model.Permission;
import ru.litvinov.springsecuritystudy2.model.Role;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                /*
                .antMatchers(HttpMethod.GET,"/api/**").hasAuthority(Permission.DEVELOPERS_READ.getPermission())
                .antMatchers(HttpMethod.POST,"/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE,"/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
                */
                .anyRequest()
                .authenticated()
                .and()
                //.httpBasic();
                .formLogin()
                .loginPage("/auth/login")
                .permitAll()
                .defaultSuccessUrl("/auth/success");
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    }

    /*
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles()
                        .authorities(Role.ADMIN.getAuthorities())
                        .build(),
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .authorities(Role.USER.getAuthorities())
                        .build()
        );
    }
*/
    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
