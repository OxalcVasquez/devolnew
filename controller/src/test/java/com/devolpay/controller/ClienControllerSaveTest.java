package com.devolpay.controller;

import com.devolpay.entity.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ClienControllerSaveTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private String uri;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        uri = "http://localhost:" + port + "/api/";
    }

    @Test
    public void saveClient(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        var client = new Client();
        client.setNombres("Jordan");
        client.setApellidos("Perez");
        client.setDni("61513129");
        client.setTelefono("937579505");
        client.setDireccion("Chiclayo");

        HttpEntity<Client> request = new HttpEntity<>(client,headers);

        var response = testRestTemplate.postForEntity(uri + "clients",request,Client.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jordan", response.getBody().getNombres());
    }
}
