package br.com.igti.posgraduacao.datasources.tasks;


import br.com.igti.posgraduacao.config.util.OrquestradorProcessVariables;
import br.com.igti.posgraduacao.datasources.MensagemDataSource;
import br.com.igti.posgraduacao.entities.OrquestradorSolicitarAberturaConta;
import br.com.igti.posgraduacao.exception.ExceptionUtil;
import br.com.igti.posgraduacao.repositories.FraudeRepository;
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
public class TaskConsultarEmailProspecto implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(TaskConsultarEmailProspecto.class);
    private FraudeRepository fraudeRepository;

    public TaskConsultarEmailProspecto(FraudeRepository fraudeRepository) {
        this.fraudeRepository = fraudeRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            log.info("TaskConsultarEmailProspecto - Inicio");

            Gson g = new Gson();
            OrquestradorSolicitarAberturaConta osac = g.fromJson(delegateExecution.getVariable(OrquestradorProcessVariables.JSON_REQ_ABERTURA_CONTA).toString(), OrquestradorSolicitarAberturaConta.class);

            final String  email = osac.getEmail();

            final Boolean isEmailFraudado = fraudeRepository.isEmailFraudado(email);

            if (isEmailFraudado) {
                delegateExecution.setVariable(OrquestradorProcessVariables.EMAIL_FRAUDADO, true);
            }else{
                delegateExecution.setVariable(OrquestradorProcessVariables.EMAIL_FRAUDADO, false);
            }

            log.info("TaskConsultarEmailProspecto - Fim");
        } catch (BpmnModelException e) {
            delegateExecution.setVariable("ERROR_TECNICO_FRAUDE_EMAIL_PROSPECTO", TaskConsultarEmailProspecto.class.getSimpleName() + " - " + e.getMessage());
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR_FRAUDE_PROSPECTO", "ERROR_FRAUDE_PROSPECTO", e.getCause());

        } catch (HttpClientErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_FRAUDE_EMAIL, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_FRAUDE);
            delegateExecution.setVariable("ERROR_TECNICO_FRAUDE_EMAIL_PROSPECTO", jsonException);
            throw new BpmnError("ERROR_FRAUDE_EMAIL_PROSPECTO", "ERROR_FRAUDE_EMAIL_PROSPECTO", e.getCause());

        } catch (HttpServerErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_FRAUDE_EMAIL, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_FRAUDE);
            delegateExecution.setVariable("ERROR_TECNICO_FRAUDE_EMAIL_PROSPECTO", jsonException);
            throw new BpmnError("ERROR_FRAUDE_EMAIL_PROSPECTO", "ERROR_FRAUDE_EMAIL_PROSPECTO", e.getCause());

        } catch (Exception e) {
            final String jsonException = ExceptionUtil.generateJsonFromException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    MensagemDataSource.MessageDataSource.ERRO_CONSULTA_FRAUDE_EMAIL, e.getMessage(),
                    MensagemDataSource.Origem.SERVICE_FRAUDE);
            delegateExecution.setVariable("ERROR_TECNICO_FRAUDE_EMAIL_PROSPECTO", jsonException);
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR_FRAUDE_EMAIL_PROSPECTO", "ERROR_FRAUDE_EMAIL_PROSPECTO", e.getCause());
        }
    }
}
