package br.com.fiap.horadaprotecao.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Nome é obrigatório")
    private UUID uuid;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    private String cpf;

    @NotBlank(message = "Telefone é obrigatório")
    @Size(min = 11, max = 11, message = "Telefone deve ter 11 dígitos")
    private String telefone;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

}
