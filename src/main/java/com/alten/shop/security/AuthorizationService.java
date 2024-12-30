package com.alten.shop.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizationService {

    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Checking admin authorization for: {}",
                authentication != null ? authentication.getName() : "null");
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            boolean isAdmin = "admin@admin.com".equals(userDetails.getUsername());
            log.info("Is admin check result: {} for user: {}", isAdmin, userDetails.getUsername());
            return isAdmin;
        }
        log.info("Not an admin - authentication invalid or not UserDetails");
        return false;
    }
}
