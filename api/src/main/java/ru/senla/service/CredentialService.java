package ru.senla.service;

import ru.senla.entity.Credential;

import java.util.List;

public interface CredentialService {

    Credential getCredentialById(Long id);

    Long saveCredential(Credential credential);

    void updateCredential(Credential credential);

    void deleteCredential(Credential credential);

    List getAllCredentials();

    void writeCredentialsToCsvFromDb();

    void readCredentialsFromCsvToDb();
}
