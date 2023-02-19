package br.com.igti.posgraduacao.mshubmensagens.datasource;

import br.com.igti.posgraduacao.mshubmensagens.datasource.repositories.MongoEmailRepository;
import br.com.igti.posgraduacao.mshubmensagens.entities.EmailHub;
import br.com.igti.posgraduacao.mshubmensagens.repositories.EmailHubRepository;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailSendGridDatasource implements EmailHubRepository {

    private final Logger log = LoggerFactory.getLogger(EmailSendGridDatasource.class);
    private MongoEmailRepository mongoEmailRepository;

    public EmailSendGridDatasource(MongoEmailRepository mongoEmailRepository) {
        this.mongoEmailRepository = mongoEmailRepository;
    }

    @Override
    public EmailHub enviarEmail(EmailHub emailHub) throws IOException {
        log.info("Enviando email");

        Mail mail = new Mail();

        Email from = new Email("carlosmedeiroslima@gmail.com");
        String subject = emailHub.getTitulo();
        Email to = new Email(emailHub.getEmailTo());
        Content content = new Content("text/plain", emailHub.getConteudo());
        mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
        log.info("Email enviado com sucesso!");
        return guardarInformacoesDeEmailEnviado(emailHub);
    }

    private EmailHub guardarInformacoesDeEmailEnviado(EmailHub emailHub) {
        return this.mongoEmailRepository.save(emailHub);


    }

}
// TODO: Utilizar o sendGrid
//https://github.com/sendgrid/sendgrid-java/blob/main/examples/mail/mail.java
