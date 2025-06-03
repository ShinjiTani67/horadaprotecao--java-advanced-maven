package br.com.fiap.horadaprotecao.controller;



import br.com.fiap.horadaprotecao.entity.Address;
import br.com.fiap.horadaprotecao.entity.FloodZone;
import br.com.fiap.horadaprotecao.repository.FloodZoneRepository;
import br.com.fiap.horadaprotecao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.fiap.horadaprotecao.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("user/home")
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FloodZoneRepository floodZoneRepository;

    @GetMapping("/home")
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
        return "home";
    }
}

