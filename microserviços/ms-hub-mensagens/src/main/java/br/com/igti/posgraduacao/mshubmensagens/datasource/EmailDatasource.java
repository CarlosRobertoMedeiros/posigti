package br.com.igti.posgraduacao.mshubmensagens.datasource;

import br.com.igti.posgraduacao.mshubmensagens.datasource.repositories.MongoEmailRepository;
import br.com.igti.posgraduacao.mshubmensagens.entities.Email;
import br.com.igti.posgraduacao.mshubmensagens.repositories.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailDatasource implements EmailRepository {

    private final Logger log = LoggerFactory.getLogger(EmailDatasource.class);
    private MongoEmailRepository mongoEmailRepository;
    private final JavaMailSender javaMailSender;

    public EmailDatasource(JavaMailSender javaMailSender,
                           MongoEmailRepository mongoEmailRepository) {
        this.javaMailSender = javaMailSender;
        this.mongoEmailRepository = mongoEmailRepository;
    }

    @Override
    public Email enviarEmail(Email email) {
        log.info("Enviando email");

        var mensagem = new SimpleMailMessage();
        mensagem.setTo(email.getEmailTo());
        mensagem.setSubject(email.getTitulo());
        mensagem.setText(email.getConteudo());

        javaMailSender.send(mensagem);
        log.info("Email enviado com sucesso!");

        return guardarInformacoesDeEmailEnviado(email);

    }

    private Email guardarInformacoesDeEmailEnviado(Email email) {
        return this.mongoEmailRepository.save(email);


    }

}
// TODO: Utilizar o sendGrid
//https://github.com/sendgrid/sendgrid-java/blob/main/examples/mail/mail.java
