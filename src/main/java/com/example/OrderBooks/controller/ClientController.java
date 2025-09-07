package com.example.OrderBooks.controller;

import com.example.OrderBooks.controller.payload.NewClientPayload;
import com.example.OrderBooks.controller.payload.UpdateClientPayload;
import com.example.OrderBooks.entity.Book;
import com.example.OrderBooks.entity.Client;
import com.example.OrderBooks.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;
    private final MessageSource messageSource;

    public ClientController(ClientService clientService, MessageSource messageSource) {
        this.clientService = clientService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public Iterable<Client> getAllClients() {
        return clientService.findAllClients();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientService.findClient(id).orElseThrow(() -> new NoSuchElementException("{library.errors.client.not_found}"));
    }

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody NewClientPayload payload,
                                        BindingResult bindingResult,
                                        UriComponentsBuilder uriComponentsBuilder)
            throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Client client = clientService.createClient(payload.fullName(), payload.birthDate());
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/api/clients/{id}")
                            .build(Map.of("id", client.getId())))
                    .body(client);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") Long id,
                                           @Valid @RequestBody UpdateClientPayload payload,
                                           BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
                clientService.updateClient(id, payload.fullName(), payload.birthDate());
            return ResponseEntity.noContent()
                    .build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent()
                .build();
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchElementException(NoSuchElementException exception,
                                                                      Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        messageSource.getMessage(exception.getMessage(), new Object[0],
                                exception.getMessage(), locale)));
    }
}
