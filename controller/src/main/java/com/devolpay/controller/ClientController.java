package com.devolpay.controller;

import com.devolpay.entity.Client;
import com.devolpay.service.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) {
        var response = clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable String id, @RequestBody Client client){
        client.setId(id);
        var response = clientService.update(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable String id){
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
