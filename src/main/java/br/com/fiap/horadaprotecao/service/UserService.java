package br.com.fiap.horadaprotecao.service;

import org.springframework.stereotype.Service;


//trocar id por uuid


@Service
@AllArgsConstructor
public class UserService {
    private final Useepository repository;

    private UserDto convertToDTO (User user){
        UserDTO dto = new UserDTO();
        //set e gets
        dto.setId(user.getId());
        return dto;
    }

    private User convertToEntity(UserDTO dto){
        User user = new User();
        //set e gets
        user.setId(dto.getId());
        return user;
    }

    public UserDTO save(UserDTO userDTO){
        User user = convertToEntity(userDTO);

        if (user.getUuid() == null || user.getUuid().isBlank()) {
            user.setId(UUID.randomUUID().toString());
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
        repository.deleteById(id);
    }

    public UserDTO findById(Uuid uuid){
        Optional<User> byUuid = repository.findByUuid(uuid);
        if (byUuid.isPresent())
            return convertToDTO(byUuid.get());{
        }
        throw new RuntimeException("Usuario com id" + uuid + "nao encontrado");
    }
}
