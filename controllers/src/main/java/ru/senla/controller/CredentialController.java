package ru.senla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.senla.dto.CredentialDto;
import ru.senla.service.CredentialService;

import java.util.List;

@RestController
@RequestMapping(value = "/credential")
@EnableResourceServer
public class CredentialController {


    @Autowired
    private CredentialService credentialService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<CredentialDto> getAllCredentials() {
        List<CredentialDto> credentialDtoList = credentialService.getAllCredentials();
        return credentialDtoList;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CredentialDto getCredentialById(@PathVariable Long id){
        CredentialDto credentialDto = credentialService.getCredentialById(id);
        return credentialDto;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Long saveCredential(@RequestBody CredentialDto credentialDto){
        Long id =  credentialService.saveCredential(credentialDto);
        return id;
    }
    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void updateCredential(@RequestBody CredentialDto credential){
        credentialService.updateCredential(credential);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void deleteCredential(@PathVariable Long id){
        credentialService.deleteCredential(id);
    }
}
