package br.com.igti.posgraduacao.mshubmensagens.datasource;

import br.com.igti.posgraduacao.mshubmensagens.datasource.repositories.MongoEmailRepository;
import br.com.igti.posgraduacao.mshubmensagens.entities.EmailHub;
import br.com.igti.posgraduacao.mshubmensagens.repositories.EmailHubRepository;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Primary
@Component
public class EmailMailjetDatasource implements EmailHubRepository {

    private final Logger log = LoggerFactory.getLogger(EmailMailjetDatasource.class);
    private MongoEmailRepository mongoEmailRepository;

    public EmailMailjetDatasource(MongoEmailRepository mongoEmailRepository) {
        this.mongoEmailRepository = mongoEmailRepository;
    }

    @Override
    public EmailHub enviarEmail(EmailHub emailHub) throws IOException {
        log.info("Enviando email");


        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(ClientOptions.builder().apiKey(System.getenv("MJ_APIKEY_PUBLIC")).apiSecretKey(System.getenv("MJ_APIKEY_PRIVATE")).build());

        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "carlosmedeiroslima@gmail.com")
                                        .put("Name", "Carlos Roberto"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", emailHub.getEmailTo())
                                                .put("Name", emailHub.getEmailTo()))) //tem que ser o nome do cliente
                                .put(Emailv31.Message.TEMPLATEID, 4609466)
                                .put(Emailv31.Message.TEMPLATELANGUAGE, true)
                                .put(Emailv31.Message.SUBJECT, emailHub.getTitulo())
                                .put(Emailv31.Message.VARIABLES, new JSONObject()
                                        .put("client", "nome cliente")
                                        .put("agencia", "agencia")
                                        .put("conta", "conta"))));
        try {
            response = client.post(request);
        } catch (MailjetException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.getStatus());
        System.out.println(response.getData());

        log.info("Email enviado com sucesso!");
        return guardarInformacoesDeEmailEnviado(emailHub);
    }

    private EmailHub guardarInformacoesDeEmailEnviado(EmailHub emailHub) {
        return this.mongoEmailRepository.save(emailHub);


    }

}
// TODO: Utilizar o sendGrid
//https://github.com/sendgrid/sendgrid-java/blob/main/examples/mail/mail.java
