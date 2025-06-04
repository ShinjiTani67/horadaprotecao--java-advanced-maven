    package br.com.fiap.horadaprotecao.config;

    import lombok.Data;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.ComponentScan;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.mail.javamail.JavaMailSender;
    import org.springframework.mail.javamail.JavaMailSenderImpl;

    import java.util.Properties;

    @Configuration
        @ConfigurationProperties(prefix = "spring.mail")
        @Data
    public class SmtpConfig {

        private String host;

        private int port;

        private Boolean auth;

        private String username;
        
        private String password;

        @Bean
        public JavaMailSender getJavaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setUsername(username);
            mailSender.setPassword(password);

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.smtp.auth", auth != null ? auth.toString() : "false");
            props.put("mail.smtp.auth", auth);

            mailSender.setHost(host);
            mailSender.setPort(port);
            
            props.put("mail.smtp.auth", auth != null ? auth.toString() : "false");

            return mailSender;

        }
    }
