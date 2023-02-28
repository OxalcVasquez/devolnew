package com.devolpay.controller;

import com.devolpay.entity.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerUpdateTest {


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
    public void testUpdateClient() {
        // create a new client
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Client newClient = new Client();
        newClient.setNombres("John");
        newClient.setApellidos("Doe");
        newClient.setDni("12345678");
        newClient.setTelefono("555-1234");
        newClient.setDireccion("123 Main St");

        HttpEntity<Client> request = new HttpEntity<>(newClient, headers);
        ResponseEntity<Client> response = restTemplate.postForEntity(uri + "clients/", request, Client.class);

        // update the client
        Client updatedClient = response.getBody();
        updatedClient.setNombres("Jane");
        updatedClient.setApellidos("Doe");

        HttpEntity<Client> updateRequest = new HttpEntity<>(updatedClient, headers);
        ResponseEntity<Client> updateResponse = restTemplate.exchange(
                uri + "clients/" + updatedClient.getId(),
                HttpMethod.PUT,
                updateRequest,
                Client.class
        );

        assertEquals("test",HttpStatus.CREATED, updateResponse.getStatusCode());
        assertNotNull(updateResponse.getBody());
        assertEquals("test","Jane", updateResponse.getBody().getNombres());
        assertEquals("test","Doe", updateResponse.getBody().getApellidos());
    }
}
