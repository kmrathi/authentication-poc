package com.auth.jwt.security;

import com.auth.jwt.dao.ApplicationUsersDao;
import com.auth.jwt.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ApplicationUsersDao applicationUsersDao ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser byUserName = applicationUsersDao.findByUserName(username);
        if(byUserName == null){
            throw new UsernameNotFoundException("User Not found");
        }
        return new UserDetailsImpl(byUserName);
    }
}
