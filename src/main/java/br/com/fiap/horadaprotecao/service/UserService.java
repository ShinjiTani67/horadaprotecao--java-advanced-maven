package br.com.fiap.horadaprotecao.service;

import br.com.fiap.horadaprotecao.dto.UserDTO;
import br.com.fiap.horadaprotecao.entity.User;
import br.com.fiap.horadaprotecao.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static jakarta.persistence.GenerationType.UUID;


//trocar id por uuid


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    private UserDTO convertToDTO (User user){
        UserDTO dto = new UserDTO();
        //set e gets
        dto.setUuid(user.getUuid());
        return dto;
    }

    private User convertToEntity(UserDTO dto){
        User user = new User();
        //set e gets
        user.setUuid(dto.getUuid());
        return user;
    }

    public UserDTO save(UserDTO userDTO){
        User user = convertToEntity(userDTO);

        if (user.getUuid() == null || user.getUuid().isBlank()) {
            user.setUuid();(UUID.randomUUID().toString());
        }

        user = (User) repository.save(user);
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

    public UserDTO findById(Uuid uuid){
        Optional<User> byUuid = repository.findByUuid(uuid);
        if (byUuid.isPresent())
            return convertToDTO(byUuid.get());{
        }
        throw new RuntimeException("Usuario com id" + uuid + "nao encontrado");
    }
}
