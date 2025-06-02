package br.com.fiap.horadaprotecao;


import br.com.fiap.horadaprotecao.dto.UserDTO;
import br.com.fiap.horadaprotecao.entity.User;
import br.com.fiap.horadaprotecao.repository.UserRepository;
import br.com.fiap.horadaprotecao.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        UUID uuid = UUID.randomUUID();
        user = new User();
        user.setUuid(uuid);
        user.setNome("Joao");
        user.setCpf("12345678901");
        user.setTelefone("11999999999");
        user.setEmail("joao@teste.com");

        userDTO = new UserDTO();
        userDTO.setUuid(uuid);
        userDTO.setNome("Joao");
        userDTO.setCpf("12345678901");
        userDTO.setTelefone("11999999999");
        userDTO.setEmail("joao@teste.com");
    }

    @Test
    void testSaveNewUser() {
        when(repository.save(any(User.class))).thenReturn(user);
        UserDTO saved = userService.save(userDTO);
        assertNotNull(saved);
        assertEquals(user.getNome(), saved.getNome());
    }

    @Test
    void testGetUser() {
        when(repository.findAll()).thenReturn(List.of(user));
        List<UserDTO> users = userService.getUser();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals(user.getNome(), users.get(0).getNome());
    }

    @Test
    void testDeleteById() {

        UUID uuid = user.getUuid();
        System.out.println("UUID usado no teste: " + uuid);

        when(repository.findByUuid(uuid)).thenReturn(Optional.of(user));

        UserDTO dto = userService.findById(uuid);

        assertNotNull(dto);
        assertEquals(user.getEmail(), dto.getEmail());
    }

    @Test
    void testFindById() {
        UUID uuid = user.getUuid();
        System.out.println("UUID para mock: " + uuid);

        when(repository.findByUuid(uuid)).thenReturn(Optional.of(user));

        System.out.println("UUID para método findById: " + uuid);
        UserDTO dto = userService.findById(uuid);

        assertNotNull(dto);
        assertEquals(user.getEmail(), dto.getEmail());
    }

    @Test
    void testFindById_NotFound() {
        UUID uuid = UUID.randomUUID();
        when(repository.findByUuid(uuid)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.findById(uuid));
        assertTrue(exception.getMessage().contains("nao encontrado"));
    }
}


