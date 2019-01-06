package com.innovate.media.controller;

import com.innovate.media.domain.Client;
import com.innovate.media.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "Client")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;
    @PostMapping(path = "/getClientList")
    public List<Client> clientList(Client client){
       return clientRepository.findAll();
    }
}

