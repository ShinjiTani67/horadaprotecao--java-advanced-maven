package br.com.fiap.horadaprotecao.entity;

import jakarta.persistence.Entity;

@Entity
public class Address {

    private String cep;

    private String rua;

    private String bairro;
}
