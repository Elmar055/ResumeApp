package com.company.service;

import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    @Qualifier("userDaoImpl1")
    private UserRepositoryCustom userRepo; // bu zaten oz interface-mizdir


    // override edilen metod budur.
    // userdetails springin oz classidir. icinde user haqqinda info saxlanilir
    // websecurity configde yazdigimiz authorizationlar bura set olunur
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // emaile gore axtarish
        User user = userRepo.findByEmail(email);

        if (user != null) {// user null-dan ferqlidirse

            // withUsername email-i set edir ve builder qaytarir
            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(email);

            // builder disabled-mi? yeni user ban edilibmi?
            builder.disabled(false);
            // user-in passwordu teyin edilir
            builder.password(user.getPassword());

            // authoritiler yaradilir
            String[] authoritiesArr = new String[]{ "ADMIN", "USER", "ROLE_USER"};

            // user-in authoritilerine bunlar elave edilir
            builder.authorities(authoritiesArr);

            // ve builder patterne gore build olunur
            return builder.build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

    }
}
