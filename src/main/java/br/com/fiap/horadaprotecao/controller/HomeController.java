package br.com.fiap.horadaprotecao.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        return "home";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:home";
    }
}
