package com.bookshop.Bookshop.axiliary;

import com.bookshop.Bookshop.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class Utils {
    private final IAuthenticationFacade authenticationFacade;

    public Utils(IAuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }


    public User getUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public boolean isLogged() {
        return getUser() != null;
    }
}
