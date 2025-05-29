package br.com.fiap.horadaprotecao.entity;

import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class Address {

    private UUID uuid;

    private String cep;

    private String rua;

    private String bairro;
}
