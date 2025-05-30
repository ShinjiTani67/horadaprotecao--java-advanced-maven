package br.com.fiap.horadaprotecao.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {

    @NotBlank(message = "ID é obrigatório")
    private UUID uuid;

    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    @NotBlank(message = "Rua é obrigatório")
    private String rua;

    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

}
