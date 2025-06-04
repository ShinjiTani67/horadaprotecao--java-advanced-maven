package br.com.horadaprotecao.controller;

import br.com.horadaprotecao.service.IAChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ia")
public class IAChatController {

    private final IAChatService iaChatService;

    public IAChatController(IAChatService iaChatService) {
        this.iaChatService = iaChatService;
    }

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> payload) {
        String userMessage = payload.get("message");
        String resposta = iaChatService.perguntarIA(userMessage);
        return ResponseEntity.ok(Map.of("response", resposta));
    }
}
