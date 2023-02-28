package com.devolpay.controller;

import com.devolpay.entity.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerListTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testGetAllClients() {
        ResponseEntity<List<Client>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/clients",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Client>>() {});

        List<Client> clients = response.getBody();

        assertEquals("test",HttpStatus.OK, response.getStatusCode());
        assertNotNull(clients);
        assertTrue(clients.size() > 0);
    }
}
