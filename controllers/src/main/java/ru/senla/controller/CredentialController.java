package ru.senla.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

public class CredentialController {


    @Autowired
    CredentialService credentialService;

    @GetMapping(value = "/all")
    public List<CredentialDto> getAllCredentials() {
        List<CredentialDto> credentialDtoList = credentialService.getAllCredentials();
        return credentialDtoList;
    }

    @GetMapping("/{id}")
    public CredentialDto getCredentialById(@PathVariable Long id){
        CredentialDto credentialDto = credentialService.getCredentialById(id);
        return credentialDto;
    }

    @PostMapping
    public Long saveCredential(@RequestBody CredentialDto credentialDto){
        Long id =  credentialService.saveCredential(credentialDto);
        return id;
    }
    @PutMapping
    public void updateCredential(@RequestBody CredentialDto credential){
        credentialService.updateCredential(credential);
    }

    @DeleteMapping("/{id}")
    public void deleteCredential(@PathVariable Long id){
        credentialService.deleteCredential(id);
    }
}
