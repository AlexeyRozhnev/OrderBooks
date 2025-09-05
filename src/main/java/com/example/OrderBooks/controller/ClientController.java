package com.example.OrderBooks.controller;

import com.example.OrderBooks.entity.Client;
import com.example.OrderBooks.service.ClientService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return this.clientService.findAll();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return this.clientService.findById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return this.clientService.save(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        client.setId(id);
        return this.clientService.save(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        this.clientService.deleteById(id);
    }
}
