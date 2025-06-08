package br.com.fiap.horadaprotecao.service;

import br.com.fiap.horadaprotecao.dto.UserDTO;
import br.com.fiap.horadaprotecao.entity.User;
import br.com.fiap.horadaprotecao.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    private UserDTO convertToDTO (User user){
        UserDTO dto = new UserDTO();
        dto.setUuid(user.getUuid());
        dto.setCpf(user.getCpf());
        dto.setNome(user.getNome());
        dto.setEmail(user.getEmail());
        dto.setTelefone(user.getTelefone());
        return dto;
    }

    private User convertToEntity(UserDTO dto){
        User user = new User();
        user.setUuid(dto.getUuid());
        user.setCpf(dto.getCpf());
        user.setEmail(dto.getEmail());
        user.setNome(dto.getNome());
        user.setTelefone(dto.getTelefone());
        return user;
    }

    public UserDTO save(UserDTO userDTO){
        User user = convertToEntity(userDTO);

        if (user.getUuid() == null ) {
            user.setUuid(UUID.randomUUID());
        }
        user = repository.save(user);
        return convertToDTO(user);
    }

    public List<UserDTO> getUser(){
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(UUID uuid){

        repository.deleteById(uuid);

    }

    public UserDTO findById(UUID uuid){

        Optional<User> byUuid = repository.findByUuid(uuid);
        if (byUuid.isPresent())
            return convertToDTO(byUuid.get());

        throw new RuntimeException("Usuario com id " + uuid + " nao encontrado");
    }

    public UserDTO findByEmail(String email) {
        Optional<User> user = repository.findByEmail(email);
        if (user.isPresent())
            return convertToDTO(user.get());
        throw new RuntimeException("Usuário com email " + email + " não encontrado");
    }

}
