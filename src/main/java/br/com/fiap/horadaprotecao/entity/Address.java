package br.com.fiap.horadaprotecao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@ToString
@Table(name="tb_address")
public class Address {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID uuid;

    @Column(nullable = false, unique = true, length = 8)
    private String cep;

    @Column(nullable = false, unique = true)
    private String rua;

    @Column(nullable = false, unique = true)
    private String bairro;

    @OneToOne(mappedBy = "address")
    private User user;
}
