package br.com.fiap.horadaprotecao.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FloodZoneDTO {

    private UUID uuid;

    private String nivelRisco;

    private Double raioEmKm;
}
