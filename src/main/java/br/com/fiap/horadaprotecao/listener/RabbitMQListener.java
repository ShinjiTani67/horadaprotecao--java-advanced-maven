package br.com.fiap.horadaprotecao.listener;

import br.com.fiap.horadaprotecao.config.RabbitMQConfig;
import br.com.fiap.horadaprotecao.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    private final EmailService emailService;

    public RabbitMQListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida: " + message);

        // Exemplo: suponha que a mensagem venha no formato "email:assunto:mensagem"
        try {
            String[] parts = message.split(":", 3);
            if (parts.length == 3) {
                String to = parts[0];
                String subject = parts[1];
                String body = parts[2];

                emailService.sendEmail(to, subject, body);
            } else {
                System.out.println("Formato de mensagem inválido. Esperado: email:assunto:mensagem");
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar mensagem: " + e.getMessage());
        }
    }
}
