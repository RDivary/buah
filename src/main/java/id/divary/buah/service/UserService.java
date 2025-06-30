package id.divary.buah.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void create(String username, String password, String role);
}
