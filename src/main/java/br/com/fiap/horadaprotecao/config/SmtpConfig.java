    package br.com.fiap.horadaprotecao.config;

    import org.springframework.beans.factory.annotation.Value;
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

        @Bean
        public JavaMailSender getJavaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

            mailSender.setHost(host);
            mailSender.setPort(port);

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.smtp.auth", false);

            return mailSender;

        }
    }
