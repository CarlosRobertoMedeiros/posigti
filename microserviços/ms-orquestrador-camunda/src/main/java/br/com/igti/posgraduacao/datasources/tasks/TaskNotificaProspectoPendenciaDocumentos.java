package br.com.igti.posgraduacao.datasources.tasks;

import br.com.igti.posgraduacao.config.util.OrquestradorProcessVariables;
import br.com.igti.posgraduacao.datasources.MensagemDataSource;
import br.com.igti.posgraduacao.datasources.feign.input.EmailHubInput;
import br.com.igti.posgraduacao.datasources.feign.output.EmailHubOutput;
import br.com.igti.posgraduacao.entities.OrquestradorSolicitarAberturaConta;
import br.com.igti.posgraduacao.exception.ExceptionUtil;
import br.com.igti.posgraduacao.repositories.NotificaProspectoPendenciaDocumentosRepository;
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
public class TaskNotificaProspectoPendenciaDocumentos implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(TaskNotificaProspectoPendenciaDocumentos.class);
    private NotificaProspectoPendenciaDocumentosRepository notificaProspectoPendenciaDocumentosRepository;

    public TaskNotificaProspectoPendenciaDocumentos(NotificaProspectoPendenciaDocumentosRepository notificaProspectoPendenciaDocumentosRepository) {
        this.notificaProspectoPendenciaDocumentosRepository = notificaProspectoPendenciaDocumentosRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            log.info("TaskNotificaClientePendenciaDocumentos - Inicio");

            Gson g = new Gson();
            OrquestradorSolicitarAberturaConta osac = g.fromJson(delegateExecution.getVariable(OrquestradorProcessVariables.JSON_REQ_ABERTURA_CONTA).toString(), OrquestradorSolicitarAberturaConta.class);

            String cpfProspecto  = osac.getCpf();
            String nomeProspecto  = osac.getNome();
            String emailProspecto  = osac.getEmail();

            String titulo = "PENDÊNCIA DE DOCUMENTOS - BANCO ABC";
            String conteudoFormatado = String.format("Prezado(a) Amigo(a)  %s ... Favor nos enviar por email, os documentos comprobatórios(RG,CPF,Últimos 3 Contra-Cheques e ou Últimos 3 meses de sua movimentação de conta em seu banco de preferência) para análise e continuação do processo de abertura de conta ",nomeProspecto);
            final EmailHubInput emailHubInput = new EmailHubInput(emailProspecto,titulo,conteudoFormatado);
            EmailHubOutput emailHubOutputData = notificaProspectoPendenciaDocumentosRepository.enviarEmail(emailHubInput);

            if (emailHubOutputData!=null) {
                delegateExecution.setVariable(OrquestradorProcessVariables.ENVIAR_EMAIL, true);
            }else{
                delegateExecution.setVariable(OrquestradorProcessVariables.ENVIAR_EMAIL, false);
            }

            log.info("TaskNotificaClientePendenciaDocumentos - Fim");
        } catch (BpmnModelException e) {
            delegateExecution.setVariable("ERROR_TECNICO_ENVIAR_EMAIL", TaskNotificaProspectoPendenciaDocumentos.class.getSimpleName() + " - " + e.getMessage());
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
