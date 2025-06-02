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

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class LoginController {

    private final UserService userService;
    private final AddressService addressService;
    private final FloodZoneService floodZoneService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String register(Model model) {
        model.addAttribute("user", new UserDTO());
        return "cadastro";
    }

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {

        String uuidString = authentication.getName();

        UUID uuid = UUID.fromString(uuidString);


        UserDTO user = userService.findById(uuid);
        model.addAttribute("user", user);

        AddressDTO address = addressService.findById(uuid);
        model.addAttribute("address", address);

        List<FloodZoneDTO> floodZones = floodZoneService.getFloodedZone();
        model.addAttribute("floodZones", floodZones);

        return "home";
    }
}
