package br.com.fiap.horadaprotecao.controller;

import br.com.fiap.horadaprotecao.dto.UserDTO;

import br.com.fiap.horadaprotecao.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Log
public class UserController {

    private final UserService service;

    @GetMapping
    public String listUser(Model model) {
        var userList = service.getUser();
        userList.forEach(u -> log.info("ID do usuário: " + u.getUuid()));
        model.addAttribute("user", userList);
        return "user";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Conectado com sucesso";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserDTO());
        return "cadastro";
    }

    @PostMapping("/save")
    public String saveUser(
            @Valid @ModelAttribute("user") UserDTO userDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            log.warning("Erros de validação ao salvar usuário:");
            bindingResult.getAllErrors().forEach(e -> log.warning(e.toString()));
            model.addAttribute("user", userDTO);
            return "user";
        }

        log.info("Salvando usuário: " + userDTO);
        service.save(userDTO);
        return "redirect:/user";
    }

    @GetMapping("/edit/{uuid}")
    public String editUser(@PathVariable UUID uuid, Model model) {
        UserDTO user = service.findById(uuid);
        model.addAttribute("user", user);
        return "userformulario";
    }

    @GetMapping("/delete/{uuid}")
    public String deleteUser(@PathVariable UUID uuid) {
        service.deleteById(uuid);
        return "redirect:/user";
    }
}