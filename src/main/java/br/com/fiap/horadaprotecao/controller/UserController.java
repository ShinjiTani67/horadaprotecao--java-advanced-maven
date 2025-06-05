package br.com.fiap.horadaprotecao.controller;

import br.com.fiap.horadaprotecao.dto.UserDTO;

import br.com.fiap.horadaprotecao.entity.Address;
import br.com.fiap.horadaprotecao.entity.FloodZone;
import br.com.fiap.horadaprotecao.entity.User;
import br.com.fiap.horadaprotecao.repository.FloodZoneRepository;
import br.com.fiap.horadaprotecao.repository.UserRepository;
import br.com.fiap.horadaprotecao.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@Log
public class UserController {

    private final UserService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FloodZoneRepository floodZoneRepository;

    @GetMapping("/list")
    public String listUser(Model model) {
        var userList = service.getUser();
        userList.forEach(u -> log.info("ID do usuário: " + u.getUuid()));
        model.addAttribute("user", userList);
        return "userlist";
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

    @PostMapping("/salvar")
    public String saveUser(
            @Valid @ModelAttribute("user") UserDTO userDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            log.warning("Erros de validação ao salvar usuário:");
            bindingResult.getAllErrors().forEach(e -> log.warning(e.toString()));
            model.addAttribute("user", userDTO);
            return "cadastro";
        }

        log.info("Salvando usuário: " + userDTO);
        service.save(userDTO);
        return "redirect:/?cadastro=sucesso";
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
        return "redirect:userlist";
    }

    @GetMapping
    public String home(Model model, Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();
            Optional<User> optionalUser = userRepository.findByEmail(email);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                model.addAttribute("user", user);

                Address address = user.getAddress();
                if (address != null) {
                    model.addAttribute("address", address);

                    List<FloodZone> floodZones = floodZoneRepository.findByAddress_Uuid(address.getUuid());
                    model.addAttribute("floodZones", floodZones);
                } else {
                    model.addAttribute("floodZones", List.of());
                }
            }
        }
        return "user";
    }
}
