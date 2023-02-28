package com.devolpay.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("prototype")
@Log4j2
@EqualsAndHashCode(callSuper=false)
@Data
@Document(collection = "client")
public class Client implements Serializable {
    @Id
    private String id;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String direccion;
}
