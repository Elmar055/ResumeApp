package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //websecurity-i aktif et
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    //userdetailservie-i implement eden bir obyekt yaradir, bu obyekt icerde loadUserbyUsername metodunu override edir
    //bizde userdetailsservice impl -i implemetn eden class CustomUserDetailsServiceImpl classidir.
    //detay ucun hemin classa baxaq
    //daxil edilmish user-i axtarir tapir bele bir user varsa return edir
    private UserDetailsService userDetailsService;

    @Autowired
    // password encoder obyekti asagidaki bean vasitesile yaradilir ve encyrpt edilmiw passwordle daxil edilen passwordu muqayise edir
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //bean vasitesile passwordu encyrpt edir
        return new BCryptPasswordEncoder();
    }


    // asagidaki setrilerde springe userdetailservice ve passwordencoder tanidilir
    // AuthenticationManagerBuilder auth-e userdeatilservice ve password encoder oturulur
    // user-i neye esasen check edileceyini ve encode edileceyini bildiririk
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)//
                .passwordEncoder(passwordEncoder);
    }


    // asagida ise hansi url-e kimin daxil ola bileceyini bildiririk, meselen /users-e sadece admin daxil olur.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/login**").permitAll() // yeni loginle baslayib sonra ne gelerse
                .and()
                .authorizeRequests().antMatchers("/users").hasAnyAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/foo").hasAnyAuthority("USER","ADMIN")
                .and()
                .authorizeRequests().anyRequest().hasAnyAuthority("ADMIN")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/users")
                    .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll();
//                .and().csrf().disable();
    }



}
