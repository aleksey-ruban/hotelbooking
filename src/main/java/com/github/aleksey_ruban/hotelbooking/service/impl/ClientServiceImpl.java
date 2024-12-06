package com.github.aleksey_ruban.hotelbooking.service.impl;

import com.github.aleksey_ruban.hotelbooking.entity.Client;
import com.github.aleksey_ruban.hotelbooking.repository.ClientRepository;
import com.github.aleksey_ruban.hotelbooking.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client create(Client client) {
        Client newClient = Client.builder()
                .phoneNumber(client.getPhoneNumber())
                .roles("CLIENT")
                .build();

        return repository.save(newClient);
    }

    @Override
    public Client updateName(Client client, String name) {
        client.setName(name);
        return repository.save(client);
    }

    @Override
    public Optional<Client> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Client> getByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public void deleteAuthorizationToken(Client client) {
        Optional<Client> newClient = repository.findById(client.getId());
        if (newClient.isPresent()) {
            Client existingClient = newClient.get();
            existingClient.setToken(null);
            repository.save(existingClient);
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
