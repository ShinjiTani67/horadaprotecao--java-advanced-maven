package br.com.fiap.horadaprotecao.controller;

import br.com.fiap.horadaprotecao.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AuthController {

    // Página de login (usada pelo Spring Security)
    @GetMapping("/login")
    public String login() {
        return "login"; // Retorna o template login.html
    }

    // Página de cadastro
    @GetMapping("/cadastro")
    public String register(Model model) {
        model.addAttribute("user", new UserDTO());
        return "cadastro"; // Retorna o template cadastro.html
    }
}
