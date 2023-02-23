package br.com.igti.posgraduacao.datasources.tasks;

import br.com.igti.posgraduacao.config.util.OrquestradorProcessVariables;
import br.com.igti.posgraduacao.datasources.MensagemDataSource;
import br.com.igti.posgraduacao.datasources.feign.input.PositivoInput;
import br.com.igti.posgraduacao.datasources.feign.output.PositivoOutput;
import br.com.igti.posgraduacao.entities.OrquestradorSolicitarAberturaConta;
import br.com.igti.posgraduacao.exception.ExceptionUtil;
import br.com.igti.posgraduacao.repositories.CadastroPositivoRepository;
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
public class TaskIncluirProspectoCadPositivo implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(TaskIncluirProspectoCadPositivo.class);
    private CadastroPositivoRepository cadastroPositivoRepository;

    public TaskIncluirProspectoCadPositivo(CadastroPositivoRepository cadastroPositivoRepository) {
        this.cadastroPositivoRepository = cadastroPositivoRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            log.info("TaskIncluirProspectoCadPositivo - Inicio");

            Gson g = new Gson();
            OrquestradorSolicitarAberturaConta osac = g.fromJson(delegateExecution.getVariable(OrquestradorProcessVariables.JSON_REQ_ABERTURA_CONTA).toString(), OrquestradorSolicitarAberturaConta.class);

            String cpf= osac.getCpf();
            String nome = osac.getNome();
            String telefone = osac.getTelefone();
            String email = osac.getEmail();


            final PositivoInput positivoInput = new PositivoInput(cpf, nome,telefone,email);
            PositivoOutput positivoOutputData = cadastroPositivoRepository.inserirPositivo(positivoInput);

            if (positivoOutputData!=null) {
                delegateExecution.setVariable(OrquestradorProcessVariables.INSERIR_POSITIVO, true);
            }else{
                delegateExecution.setVariable(OrquestradorProcessVariables.INSERIR_POSITIVO, false);
            }

            log.info("TaskIncluirProspectoCadPositivo - Fim");
        } catch (BpmnModelException e) {
            delegateExecution.setVariable("ERROR_TECNICO_CAD_POSITIVO_INCLUIR_PROSPECTO", TaskIncluirProspectoCadPositivo.class.getSimpleName() + " - " + e.getMessage());
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR_CAD_POSITIVO_INCLUIR_PROSPECTO", "ERROR_CAD_POSITIVO_INCLUIR_PROSPECTO", e.getCause());

        } catch (HttpClientErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_INCLUIR_PROSPECTO_CADASTRO_POSITIVO, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_CADASTRO_POSITIVO);
            delegateExecution.setVariable("ERROR_TECNICO_CAD_POSITIVO_INCLUIR_PROSPECTO", jsonException);
            throw new BpmnError("ERROR_CAD_POSITIVO_INCLUIR_PROSPECTO", "ERROR_CAD_POSITIVO_INCLUIR_PROSPECTO", e.getCause());

        } catch (HttpServerErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_INCLUIR_PROSPECTO_CADASTRO_POSITIVO, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_CADASTRO_POSITIVO);
            delegateExecution.setVariable("ERROR_TECNICO_CAD_POSITIVO_INCLUIR_PROSPECTO", jsonException);
            throw new BpmnError("ERROR_CAD_POSITIVO_INCLUIR_PROSPECTO", "ERROR_CAD_POSITIVO_INCLUIR_PROSPECTO", e.getCause());

        } catch (Exception e) {
            final String jsonException = ExceptionUtil.generateJsonFromException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    MensagemDataSource.MessageDataSource.ERRO_INCLUIR_PROSPECTO_CADASTRO_POSITIVO, e.getMessage(),
                    MensagemDataSource.Origem.SERVICE_CADASTRO_POSITIVO);
            delegateExecution.setVariable("ERROR_TECNICO_CAD_POSITIVO_INCLUIR_PROSPECTO", jsonException);
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR_CAD_POSITIVO_INCLUIR_PROSPECTO", "ERROR_CAD_POSITIVO_INCLUIR_PROSPECTO", e.getCause());
        }




    }
}
