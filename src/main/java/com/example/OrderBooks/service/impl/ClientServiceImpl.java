package com.example.OrderBooks.service.Impl;

import com.example.OrderBooks.entity.Client;
import com.example.OrderBooks.repository.ClientRepository

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Iterable<Client> findAllClient() {
            return this.clientRepository.findAll();
        }
    }

    @Override
    @Transactional
    public Client createClient(Client client) {
        return this.clientRepository.save(new Product(client));
    }

    @Override
    public Optional<Client> findClient(Long id) {
        return this.clientRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateClient(Client client) {
        this.clientRepository.findById(id)
                .ifPresentOrElse(newClient -> {
					newClient.setfullName(client.getfullName);
                    newClient.setbirthDate(client.getbirthDate);
                    newClient.setAuthor(author);
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