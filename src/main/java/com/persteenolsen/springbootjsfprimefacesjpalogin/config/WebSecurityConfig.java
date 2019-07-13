package com.persteenolsen.springbootjsfprimefacesjpalogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// NOTE: For production because the password is NOT in plain-text but match a plain text
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
   // NOTE: To be used in production 
   // Produced from BCrypt-Generator "BcryptEncoderController" 
   // and the plain text password "persteen1967" must be
   // removed in a real world application before deployment to production!
   private String ENCODED_PASSWORD = "$2a$10$BU4mPFHW8stXWMVH8clcZ.yZ7wl54oJq.f0Lu2HnUK6.WdooEoTZ2";
     
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

      // I am using inMemoryAuthentication but it is possible to use JDBC anbd LDAP too!
      auth.inMemoryAuthentication()
         .passwordEncoder(passwordEncoder())
         .withUser("user").password(ENCODED_PASSWORD).roles("USER");
     }
  
   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
    
   @Override
    protected void configure(HttpSecurity http) throws Exception {

      
        http
           .authorizeRequests()
            
            
            // The list of pages/views the users can request without being authenticated
            .antMatchers("/", "/welcome.xhtml", "/listpersonspublic.xhtml", "/menu.xhtml", "/css/style.css" ).permitAll()
            
            // NOTE: This is needed for PrimeFaces Menu and DOM Elements to be displayed correct!!
            .antMatchers("/javax.faces.resource/**").permitAll()
            // .antMatchers("/resources/**").permitAll()

            .anyRequest().authenticated()

           .and()
              .formLogin()
              // With no costum login page the default Spring Boot Security login 
              // page will be displayed when the user try to request a page in which he is not authenticated
              // An excample of a costum login page!!!
              //.loginPage("/login.xhtml")
              
              .defaultSuccessUrl("/listpersons.xhtml")
              .failureUrl("/login?error=true")

              .permitAll()
            .and()
                 .logout()
                               
                 // NOTE: Maybe it is not needed due to Spring logout handling behind the scene
                 .deleteCookies("JSESSIONID")

                 // If logout was ok, back to welcome/about page
                .logoutSuccessUrl("/welcome.xhtml")
                
                // NOTE: Maybe it is not needed due to Spring logout handling behind the scene
                .invalidateHttpSession(true)

                .permitAll()
             .and()
                              
               // Now csrf is disabled and it is possible to perform logout GET like in link
               // Note: csrf is enabled by default and then logout is performed by POST
               .csrf()
               .disable();
              

    }


}