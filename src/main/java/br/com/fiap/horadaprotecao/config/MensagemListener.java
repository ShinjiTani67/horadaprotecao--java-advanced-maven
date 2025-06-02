package br.com.fiap.horadaprotecao.config;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MensagemListener {

    @RabbitListener(queues = br.com.fiap.horadaprotecao.config.RabbitMQConfig.QUEUE_NAME)
    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem recebida: " + mensagem);
    }
}