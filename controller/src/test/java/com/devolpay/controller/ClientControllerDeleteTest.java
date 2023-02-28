package com.devolpay.controller;

import com.devolpay.entity.Client;
import org.junit.Before;
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
public class ClientControllerDeleteTest {


    @Autowired
    private TestRestTemplate restTemplate;
    private String uri;
    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        uri = "http://localhost:" + port + "/api/";
    }

    @Test
    public void deleteClient() {
        // Create a client to delete
        var client = new Client();
        client.setNombres("David");
        client.setApellidos("Perez");
        client.setDni("61513129");
        client.setTelefono("937579505");
        client.setDireccion("Chiclayo");
        var createdClient = restTemplate.postForEntity(uri + "clients", client, Client.class).getBody();

        // Delete the client
        var response = restTemplate.exchange(uri + "clients/" + createdClient.getId(), HttpMethod.DELETE, null, Client.class);
        // Check that the response has a 200 status code
        assertEquals("test",HttpStatus.OK, response.getStatusCode());

        // Try to get the deleted client and check that it's not found
        var getResponse = restTemplate.exchange(uri + "clients/" + createdClient.getId(), HttpMethod.DELETE,null,Client.class);
        assertEquals("test delete",HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }
}
