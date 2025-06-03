package br.com.fiap.horadaprotecao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.UUID;

@Data
@Entity
@ToString
@Table(name = "tb_floodzone")
public class FloodZone {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID uuid;

    private String nivelRisco;

    private Double raioEmKm;

    @ManyToOne
    private Address address;

}
