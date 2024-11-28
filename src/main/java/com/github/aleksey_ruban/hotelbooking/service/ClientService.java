package com.github.aleksey_ruban.hotelbooking.service;

import com.github.aleksey_ruban.hotelbooking.entity.Client;

public interface ClientService {
    Client create(Client client);
    Client updateName(Client client, String name);
    Client getById(Long id);
    Client getByPhoneNumber(String phoneNumber);
    void delete(Long id);
}
