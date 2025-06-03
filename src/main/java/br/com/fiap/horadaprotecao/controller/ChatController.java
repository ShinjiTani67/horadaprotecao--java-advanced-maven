package br.com.fiap.horadaprotecao.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatClient chatClient;

    private final String KEY = "chatMessages";

    @GetMapping("/")
    public String chatPage(HttpSession session, Model model) {
        List<String> messages = getSessionMessages(session);
        model.addAttribute("messages", messages);
        return "chat";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String userMessage, HttpSession session, Model model) {

        List<String> messages = getSessionMessages(session);

        messages.add("Você: " + userMessage);
        String response = chatClient.
                prompt(userMessage).
                call().
                content();
        messages.add("ChatGTP: " + response);

        session.setAttribute(KEY, messages);
        model.addAttribute("messages", messages);

        return "chat";
    }

    private List<String> getSessionMessages(HttpSession session) {

        List<String> messages = (List<String>) session.getAttribute(KEY);
        if (messages == null) {
            messages = new ArrayList<>();
            session.setAttribute(KEY, messages);
        }
        return messages;
    }


}