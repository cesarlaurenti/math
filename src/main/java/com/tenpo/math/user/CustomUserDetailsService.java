package com.tenpo.math.user;

import java.util.stream.Collectors;

import com.tenpo.math.exception.DuplicatedEntityException;
import com.tenpo.math.exception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements ICustomUserDetailService {

    private final UserRepository repo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository repo, PasswordEncoder encoder){
        this.repo = repo;
        this.passwordEncoder = encoder;
    }

    final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo
                .findByUsername(username)
                .map(u -> new org.springframework.security.core.userdetails.User(
                        u.getUsername(),
                        u.getPassword(),
                        u.isActive(),
                        u.isActive(),
                        u.isActive(),
                        u.isActive(),
                        AuthorityUtils.createAuthorityList(
                                u.getRoles()
                                        .stream()
                                        .map(r -> "ROLE_" + r.getName().toUpperCase())
                                        .collect(Collectors.toList())
                                        .toArray(new String[]{}))))
                .orElseThrow(() -> new UsernameNotFoundException("No user with "
                        + "the name " + username + "was found in the database"));
    }

    public void registerUser(AppUser user){
        user.setActive(true);
        try {
            save(user);
        } catch (DataIntegrityViolationException ex) {
            logger.error(ex.getMessage());
            throw new DuplicatedEntityException("Usuario");
        }
    }

    private void save(AppUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    }

}
