package com.tenpo.math.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface ICustomUserDetailService extends UserDetailsService {

    void registerUser(AppUser user);

}
