package com.github.aleksey_ruban.hotelbooking.service;

import com.github.aleksey_ruban.hotelbooking.entity.Client;

import java.util.Optional;

public interface ClientService {
    Client create(Client client);
    Client updateName(Client client, String name);
    Optional<Client> getById(Long id);
    Optional<Client> getByPhoneNumber(String phoneNumber);
    void deleteAuthorizationToken(Client client);
    void delete(Long id);
}
