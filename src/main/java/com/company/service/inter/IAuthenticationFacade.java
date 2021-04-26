package com.company.service.inter;

import com.company.entity.User;
import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {

    Authentication getAuthentication();

    User getCurrentUser();
}
