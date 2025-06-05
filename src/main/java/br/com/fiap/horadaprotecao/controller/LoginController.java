package br.com.fiap.horadaprotecao.controller;

import br.com.fiap.horadaprotecao.dto.AddressDTO;
import br.com.fiap.horadaprotecao.dto.FloodZoneDTO;
import br.com.fiap.horadaprotecao.dto.UserDTO;
import br.com.fiap.horadaprotecao.service.AddressService;
import br.com.fiap.horadaprotecao.service.FloodZoneService;
import br.com.fiap.horadaprotecao.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/cadastro")
public class LoginController {

    @GetMapping("/cadastro")
    public String register(Model model) {
        model.addAttribute("user", new UserDTO());
        return "cadastro";
    }
}
