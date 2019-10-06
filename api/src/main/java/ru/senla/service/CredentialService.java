package ru.senla.service;

import ru.senla.dto.CredentialDto;
import ru.senla.entity.Credential;

import java.util.List;

public interface CredentialService {

    CredentialDto getCredentialById(Long id);

    Long saveCredential(CredentialDto credential);

    void updateCredential(CredentialDto credential);

    void deleteCredential(Long id);

    List getAllCredentials();

    Credential getCredentialByLogin(String login);

}
