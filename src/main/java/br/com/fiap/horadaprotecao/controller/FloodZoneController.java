package br.com.fiap.horadaprotecao.controller;

import br.com.fiap.horadaprotecao.dto.FloodZoneDTO;
import br.com.fiap.horadaprotecao.service.FloodZoneService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/floodzone")
@AllArgsConstructor
@Log
public class FloodZoneController {

    private final FloodZoneService service;

    @GetMapping
    public String listFloodZones(Model model) {
        var floodZones = service.getFloodedZone();
        floodZones.forEach(fz -> log.info("ID da zona de alagamento: " + fz.getUuid()));
        model.addAttribute("floodzoneList", floodZones);
        return "floodzone";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "FloodZone funcionando!";
    }

    @GetMapping("/new")
    public String newFloodZone(Model model) {
        model.addAttribute("floodzone", new FloodZoneDTO());
        return "floodzoneformulario";
    }

    @PostMapping("/save")
    public String saveFloodZone(
            @Valid @ModelAttribute("floodzone") FloodZoneDTO floodZoneDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            log.warning("Erros de validação ao salvar zona de alagamento:");
            bindingResult.getAllErrors().forEach(e -> log.warning(e.toString()));
            model.addAttribute("floodzone", floodZoneDTO);
            return "floodzoneformulario";
        }

        log.info("Salvando zona de alagamento: " + floodZoneDTO);
        service.save(floodZoneDTO);
        return "redirect:floodzone";
    }

    @GetMapping("/edit/{id}")
    public String editFloodZone(@PathVariable UUID id, Model model) {
        FloodZoneDTO floodZone = service.findByUuid(id);
        model.addAttribute("floodzone", floodZone);
        return "floodzoneformulario";
    }

    @GetMapping("/delete/{id}")
    public String deleteFloodZone(@PathVariable UUID id) {
        service.deleteById(id);
        return "redirect:floodzone";
    }
}