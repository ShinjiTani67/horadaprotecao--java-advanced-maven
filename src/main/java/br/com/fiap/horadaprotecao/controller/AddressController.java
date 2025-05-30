package br.com.fiap.horadaprotecao.controller;

import br.com.fiap.horadaprotecao.dto.AddressDTO;
import br.com.fiap.horadaprotecao.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@Controller
@RequestMapping("/address")
@AllArgsConstructor
@Log
public class AddressController {

    private final AddressService service;

    @GetMapping
    public String listAddresses(Model model) {
        var addresses = service.getAddress();
        addresses.forEach(a -> log.info("ID do endereço: " + a.getUuid()));
        model.addAttribute("addressList", addresses);
        return "address"; // Nome da página HTML para listar endereços
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Endereço funcionando!";
    }

    @GetMapping("/new")
    public String newAddress(Model model) {
        model.addAttribute("address", new AddressDTO());
        return "addressformulario"; // Nome da página HTML do formulário
    }

    @PostMapping("/save")
    public String saveAddress(
            @Valid @ModelAttribute("address") AddressDTO addressDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            log.warning("Erros de validação ao salvar endereço:");
            bindingResult.getAllErrors().forEach(e -> log.warning(e.toString()));
            model.addAttribute("address", addressDTO);
            return "addressformulario";
        }

        log.info("Salvando endereço: " + addressDTO);
        service.save(addressDTO);
        return "redirect:/address";
    }

    @GetMapping("/edit/{id}")
    public String editAddress(@PathVariable UUID id, Model model) {
        AddressDTO address = service.findById(id);
        model.addAttribute("address", address);
        return "addressformulario";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable UUID id) {
        service.deleteById(id);
        return "redirect:/address";
    }
}