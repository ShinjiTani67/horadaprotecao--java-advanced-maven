package br.com.fiap.horadaprotecao.entity;

import jakarta.persistence.Entity;

@Entity
public class User {
    private String nome;
    private String cpf;
    private String telefone;
}
