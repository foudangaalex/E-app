package e.commerce.Eapp.security;

import e.commerce.Eapp.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ConfigSecurity {

    private UserDetailsServiceImpl userDetailsServiceImpl;

    //@Bean
    JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
   // @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password("{noop}1234").roles("ADMIN").build(),
                User.withUsername("user2").password("{noop}1234").roles("USER").build()
        );
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       // httpSecurity.csrf().disable();
        httpSecurity.formLogin().defaultSuccessUrl("/admin/clients");
        httpSecurity.authorizeHttpRequests().antMatchers("/admin/***").hasRole("ADMIN");
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.userDetailsService(userDetailsServiceImpl);
        return httpSecurity.build();


    }

}
