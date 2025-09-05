package com.example.OrderBooks.service;

import com.example.OrderBooks.entity.Client;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface ClientService {

    Iterable<Client> findAllClients(String filter);

    Client createClient(String fullName, LocalDate birthDate);

    Optional<Client> findClient(Long id);

    void updateClient(Long id, String fullName, LocalDate birthDate);

    void deleteClient(Long id);
}
