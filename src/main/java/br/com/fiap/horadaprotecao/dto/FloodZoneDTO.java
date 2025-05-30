package br.com.fiap.horadaprotecao.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FloodZoneDTO {

    @NotBlank(message = "ID é obrigatório")
    private UUID uuid;

    @NotBlank(message = "nivel de risco não valido")
    private String nivelRisco;

    @NotBlank(message = "Raio não é valido")
    private Double raioEmKm;
}
