package ru.senla.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.senla.entity.Credential;
import ru.senla.service.CredentialService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomDetailsService implements UserDetailsService {
    @Autowired
    CredentialService credentialService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Credential credential = credentialService.getCredentialByLogin(username);
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + credential.getRole().toUpperCase());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        UserDetails userDetails = new User(credential.getLogin(),
                credential.getPassword(), authorities);


        return userDetails;
    }
}