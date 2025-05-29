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

        if (user.getId() == null || user.getId().isBlank()) {
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

    public void deleteById(String id){
        repository.deleteById(id);
    }

    public UserDTO findById(String id){
        Optional<User> byId = repository.findById(id);
        if (byId.isPresent())
            return convertToDTO(byId.get());{
        }
        throw new RuntimeException("Usuario com id" + id + "nao encontrado");
    }
}
