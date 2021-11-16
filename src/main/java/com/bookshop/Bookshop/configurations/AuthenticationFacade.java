package com.bookshop.Bookshop.configurations;

import org.springframework.security.core.Authentication;
import com.bookshop.Bookshop.configurations.IAuthenticationFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}