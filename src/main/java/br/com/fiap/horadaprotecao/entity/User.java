package br.com.fiap.horadaprotecao.entity;

import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class User {

    private UUID uuid;

    private String nome;

    private String cpf;

    private String telefone;

    private String email;

}
