package br.com.fiap.horadaprotecao.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {

    public UUID uuid;

    public String cep;

    public String rua;

    public String bairro;

}
