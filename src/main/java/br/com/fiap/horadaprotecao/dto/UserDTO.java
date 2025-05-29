package br.com.fiap.horadaprotecao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    public UUID uuid;

    public String nome;

    public String cpf;

    public String telefone;

    public String email;

}
