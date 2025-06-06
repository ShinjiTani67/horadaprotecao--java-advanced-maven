package br.com.fiap.horadaprotecao.controller;

import br.com.fiap.horadaprotecao.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String register(Model model) {
        model.addAttribute("user", new UserDTO());
        return "cadastro";
    }
}
