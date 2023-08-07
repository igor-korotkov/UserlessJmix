package com.ikorotkov.userlessjmix.app;

import io.jmix.core.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component("UJ_BasicUserRepository")
public class BasicUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(BasicUserRepository.class);

    protected UserDetails systemUser;
    protected UserDetails anonymousUser;
    public BasicUserRepository() {
        initServiceUsers();
    }

    protected void initServiceUsers() {
        systemUser = createSystemUser();
        anonymousUser = createAnonymousUser();
    }

    protected UserDetails createSystemUser() {
        return User.builder()
                .username("system")
                .password("{noop}")
                .authorities(Collections.emptyList())
                .build();
    }

    protected UserDetails createAnonymousUser() {
        return User.builder()
                .username("anonymous")
                .password("{noop}")
                .authorities(Collections.emptyList())
                .build();
    }

    protected UserDetails createEmptyUser() {
        return User.builder()
                .username("{noop}")
                .password("{noop}")
                .authorities(Collections.emptyList())
                .build();
    }

    @Override
    public UserDetails getSystemUser() {
        return systemUser;
    }

    @Override
    public UserDetails getAnonymousUser() {
        return anonymousUser;
    }

    @Override
    public List<? extends UserDetails> getByUsernameLike(String substring) {
        log.debug("call of UserRepository.getByUsernameLike(\"{}\")", substring);
        return Collections.emptyList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("call of UserRepository.loadUserByUsername(\"{}\")", username);
        return createEmptyUser();
    }
}