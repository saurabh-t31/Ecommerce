package com.project3.Ecommerce.Configuration;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project3.Ecommerce.Service.CustomerUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    CustomerUserDetailService customerUserDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

         return httpSecurity
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests(request-> request
                .requestMatchers("/images/**", "/css/**", "/js/**", "/productImages/**","/shop").permitAll()
                .requestMatchers("/","/home","/register/**").permitAll()
                // .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
)
.formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/login")
                            .defaultSuccessUrl("/home")
                            .failureUrl("/login?error=true")
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .permitAll();
                            
                })
                .logout(logout -> logout
                .logoutUrl("/logout")  // URL for logout
                .logoutSuccessUrl("/login?logout=true")  // Redirect after logout
                .invalidateHttpSession(true)  // Invalidate session
                .deleteCookies("JSESSIONID")  // Delete cookies on logout
                .permitAll()
            )

                .build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(customerUserDetailService);
	    provider.setPasswordEncoder(bCryptPasswordEncoder());

		return provider;
	}
}
