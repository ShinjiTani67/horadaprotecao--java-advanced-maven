package br.com.fiap.horadaprotecao.controller;

import br.com.fiap.horadaprotecao.dto.UserDTO;
import br.com.fiap.horadaprotecao.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.save(userDTO);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID uuid) {
        UserDTO user = userService.findById(uuid);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID uuid) {
        userService.deleteById(uuid);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save")
    public String salveUser(
            @Valid @ModelAttribute("user") UserDTO userDTO,
            BindingResult bindingResult,
            Model model
    ){
        if (bindingResult.hasErrors()) {
            log.warning("Erros de validacao ao salvar usuario:");
            bindingResult.getAllErrors().forEach(e -> log.warning(e.toString()));
            model.addAttribute("user", userDTO);
            return "userformulario";
        }
        log.info("Salvando usuario: " + userDTO);
        service.save(userDTO);
        return "redirect:/user";
    }
}