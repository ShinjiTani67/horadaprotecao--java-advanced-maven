package br.com.fiap.horadaprotecao.controller;

import br.com.fiap.horadaprotecao.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class DashboardController {

    private final UserService userService;
    private final AddressService addressService;
    private final FloodZoneService floodZoneService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        String email = authentication.getName();
        UserDTO user = userService.findByEmail(email);
        model.addAttribute("user", user);

        AddressDTO address = addressService.findByUserEmail(email);
        model.addAttribute("address", address);

        List<FloodZoneDTO> floodZones = floodZoneService.getFloodedZone();
        model.addAttribute("floodZones", floodZones);

        return "home"; // ou "dashboard.html", se você quiser renomear a view
    }
}
