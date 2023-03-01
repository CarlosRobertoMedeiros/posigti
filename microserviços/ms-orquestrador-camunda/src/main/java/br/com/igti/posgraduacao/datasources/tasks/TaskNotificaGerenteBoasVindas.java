package br.com.igti.posgraduacao.datasources.tasks;

import br.com.igti.posgraduacao.config.util.OrquestradorProcessVariables;
import br.com.igti.posgraduacao.datasources.MensagemDataSource;
import br.com.igti.posgraduacao.datasources.feign.input.EmailHubInput;
import br.com.igti.posgraduacao.datasources.feign.output.EmailHubOutput;
import br.com.igti.posgraduacao.entities.OrquestradorSolicitarAberturaConta;
import br.com.igti.posgraduacao.exception.ExceptionUtil;
import br.com.igti.posgraduacao.repositories.NotificaClienteRepository;
import br.com.igti.posgraduacao.repositories.NotificaGerenteBoasVindasRepository;
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
public class TaskNotificaGerenteBoasVindas implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(TaskNotificaGerenteBoasVindas.class);
    private NotificaGerenteBoasVindasRepository notificaGerenteBoasVindasRepository;

    public TaskNotificaGerenteBoasVindas(NotificaGerenteBoasVindasRepository notificaGerenteBoasVindasRepository) {
        this.notificaGerenteBoasVindasRepository = notificaGerenteBoasVindasRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            log.info("TaskNotificaGerenteBoasVindas - Inicio");

            Gson g = new Gson();
            OrquestradorSolicitarAberturaConta osac = g.fromJson(delegateExecution.getVariable(OrquestradorProcessVariables.JSON_REQ_ABERTURA_CONTA).toString(), OrquestradorSolicitarAberturaConta.class);

            String agenciaCliente  = g.fromJson(delegateExecution.getVariable("agencia").toString(), String.class);
            String contaCliente  = g.fromJson(delegateExecution.getVariable("conta").toString(), String.class);

            String cpf = osac.getCpf();
            String nome = osac.getNome();
            String endereco = osac.getEndereco();
            String telefone = osac.getTelefone();
            String cep = osac.getCep();
            Double renda = osac.getRenda();
            Double cartaoDebito = osac.getCartaoDebito();
            Double emprestimo = osac.getEmprestimo();
            String emailGerente = "carlosmedeiroslima@gmail.com";

            String titulo = "NOTIFICAÇÃO DE NOVO CLIENTE";
            String conteudoFormatado = String.format("Caro(a) Gerente(a) ... Antonio Nunes, favor realizar o primeiro contato com o cliente %s referente as boas vindas. Sua agência é: %s e sua conta é %s",osac.getNome(), agenciaCliente,contaCliente);
            final EmailHubInput emailHubInput = new EmailHubInput(emailGerente,titulo,conteudoFormatado);
            EmailHubOutput emailHubOutputData = notificaGerenteBoasVindasRepository.enviarEmail(emailHubInput);

            if (emailHubOutputData!=null) {
                delegateExecution.setVariable(OrquestradorProcessVariables.ENVIAR_EMAIL, true);
            }else{
                delegateExecution.setVariable(OrquestradorProcessVariables.ENVIAR_EMAIL, false);
            }

            log.info("TaskNotificaGerenteBoasVindas - Fim");
        } catch (BpmnModelException e) {
            delegateExecution.setVariable("ERROR_TECNICO_ENVIAR_EMAIL", TaskNotificaGerenteBoasVindas.class.getSimpleName() + " - " + e.getMessage());
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
