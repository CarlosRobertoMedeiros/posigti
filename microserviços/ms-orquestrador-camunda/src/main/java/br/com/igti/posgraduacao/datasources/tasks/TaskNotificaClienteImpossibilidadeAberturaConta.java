package br.com.igti.posgraduacao.datasources.tasks;

import br.com.igti.posgraduacao.config.util.OrquestradorProcessVariables;
import br.com.igti.posgraduacao.datasources.MensagemDataSource;
import br.com.igti.posgraduacao.datasources.feign.input.EmailHubInput;
import br.com.igti.posgraduacao.datasources.feign.output.EmailHubOutput;
import br.com.igti.posgraduacao.entities.OrquestradorSolicitarAberturaConta;
import br.com.igti.posgraduacao.exception.ExceptionUtil;
import br.com.igti.posgraduacao.repositories.NotificaClienteRepository;
import com.google.gson.Gson;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.model.bpmn.BpmnModelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Component
public class TaskNotificaClienteImpossibilidadeAberturaConta implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(TaskNotificaClienteImpossibilidadeAberturaConta.class);
    private NotificaClienteRepository notificaClienteRepository;

    public TaskNotificaClienteImpossibilidadeAberturaConta(NotificaClienteRepository notificaClienteRepository) {
        this.notificaClienteRepository = notificaClienteRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            log.info("TaskNotificaClienteImpossibilidadeAberturaConta - Inicio");

            String cpfProspecto  = delegateExecution.getVariable("cpf").toString();
            String nomeProspecto  = delegateExecution.getVariable("nome").toString();
            String emailProspecto  = delegateExecution.getVariable("email").toString();

            String titulo = "IMPORTANTE - BANCO ABC";
            String conteudoFormatado = String.format("Prezado(a) Amigo(a)  %s ... 'Infelizmente' nesse momento não conseguimos realizar a sua abertura de conta, entraremos em contato contigo no futuro, agradecemos a preferência !",nomeProspecto);
            final EmailHubInput emailHubInput = new EmailHubInput(emailProspecto,titulo,conteudoFormatado);
            EmailHubOutput emailHubOutputData = notificaClienteRepository.enviarEmail(emailHubInput);

            if (emailHubOutputData!=null) {
                delegateExecution.setVariable(OrquestradorProcessVariables.ENVIAR_EMAIL, true);
            }else{
                delegateExecution.setVariable(OrquestradorProcessVariables.ENVIAR_EMAIL, false);
            }

            log.info("TaskNotificaClienteImpossibilidadeAberturaConta - Fim");
        } catch (BpmnModelException e) {
            delegateExecution.setVariable("ERROR_TECNICO_ENVIAR_EMAIL", TaskNotificaClienteImpossibilidadeAberturaConta.class.getSimpleName() + " - " + e.getMessage());
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR_ENVIAR_EMAIL", "ERROR_ENVIAR_EMAIL", e.getCause());

        } catch (HttpClientErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_ENVIAR_EMAIL, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_HUB_MENSAGENS);
            delegateExecution.setVariable("ERROR_TECNICO_ENVIAR_EMAIL", jsonException);
            throw new BpmnError("ERROR_ENVIAR_EMAIL", "ERROR_ENVIAR_EMAIL", e.getCause());

        } catch (HttpServerErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_ENVIAR_EMAIL, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_HUB_MENSAGENS);
            delegateExecution.setVariable("ERROR_TECNICO_ENVIAR_EMAIL", jsonException);
            throw new BpmnError("ERROR_ENVIAR_EMAIL", "ERROR_ENVIAR_EMAIL", e.getCause());

        } catch (Exception e) {
            final String jsonException = ExceptionUtil.generateJsonFromException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    MensagemDataSource.MessageDataSource.ERRO_ENVIAR_EMAIL, e.getMessage(),
                    MensagemDataSource.Origem.SERVICE_HUB_MENSAGENS);
            delegateExecution.setVariable("ERROR_TECNICO_ENVIAR_EMAIL", jsonException);
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR_ENVIAR_EMAIL", "ERROR_ENVIAR_EMAIL", e.getCause());
        }




    }
}
