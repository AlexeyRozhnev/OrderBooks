package com.example.OrderBooks.service.impl;

import com.example.OrderBooks.entity.Client;
import com.example.OrderBooks.repository.ClientRepository;
import com.example.OrderBooks.service.ClientService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Iterable<Client> findAllClients() {
        return this.clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client createClient(String fullName, LocalDate birthDate) {
        return this.clientRepository.save(new Client(null, fullName, birthDate));
    }

    @Override
    public Optional<Client> findClient(Long id) {
        return this.clientRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateClient(Long id, String fullName, LocalDate birthDate) {
        this.clientRepository.findById(id)
                .ifPresentOrElse(client -> {
					client.setFullName(fullName);
                    client.setBirthDate(birthDate);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        this.clientRepository.deleteById(id);
    }
}