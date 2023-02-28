package com.devolpay.service.impl;

import com.devolpay.dao.impl.ClientRepository;
import com.devolpay.entity.Client;
import com.devolpay.service.inter.BaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class ClientService implements BaseService<Client> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    @Override
    public Client save(Client client) {
        clientRepository.insert(client);
        return client;
    }

    @Override
    public Client update(Client client) {
        clientRepository.update(client);
        return client;
    }

    @Override
    public void delete(String id) {
        clientRepository.delete(id);
    }

}
