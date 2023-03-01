package br.com.igti.posgraduacao.datasources.tasks;

import br.com.igti.posgraduacao.config.util.OrquestradorProcessVariables;
import br.com.igti.posgraduacao.datasources.MensagemDataSource;
import br.com.igti.posgraduacao.datasources.feign.input.ClienteInput;
import br.com.igti.posgraduacao.datasources.feign.output.ClienteOutput;
import br.com.igti.posgraduacao.entities.OrquestradorSolicitarAberturaConta;
import br.com.igti.posgraduacao.exception.ExceptionUtil;
import br.com.igti.posgraduacao.repositories.CadastroClienteRepository;
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
public class TaskIncluirCliente implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(TaskIncluirCliente.class);
    private CadastroClienteRepository cadastroClienteRepository;

    public TaskIncluirCliente(CadastroClienteRepository cadastroClienteRepository) {
        this.cadastroClienteRepository = cadastroClienteRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            log.info("TaskIncluirCliente - Inicio");

            Gson g = new Gson();
            OrquestradorSolicitarAberturaConta osac = g.fromJson(delegateExecution.getVariable(OrquestradorProcessVariables.JSON_REQ_ABERTURA_CONTA).toString(), OrquestradorSolicitarAberturaConta.class);

            String cpf = osac.getCpf();
            String nome = osac.getNome();
            String endereco = osac.getEndereco();
            String telefone = osac.getTelefone();
            String cep = osac.getCep();
            Double renda = osac.getRenda();
            Double cartaoDebito = osac.getCartaoDebito();
            Double emprestimo = osac.getEmprestimo();
            String email = osac.getEmail();

            final ClienteInput clienteInput = new ClienteInput(cpf, nome, endereco, telefone, cep, renda, cartaoDebito, emprestimo, email);
            ClienteOutput clienteOutputData = cadastroClienteRepository.inserirCliente(clienteInput);

            if (clienteOutputData!=null) {
                delegateExecution.setVariable(OrquestradorProcessVariables.INSERIR_CLIENTE, true);
                delegateExecution.setVariable("agencia", clienteOutputData.getAgencia());
                delegateExecution.setVariable("conta", clienteOutputData.getConta());
            }else{
                delegateExecution.setVariable(OrquestradorProcessVariables.INSERIR_CLIENTE, false);
            }

            log.info("TaskIncluirCliente - Fim");
        } catch (BpmnModelException e) {
            delegateExecution.setVariable("ERROR_TECNICO_INSERIR_CLIENTE", TaskIncluirCliente.class.getSimpleName() + " - " + e.getMessage());
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR_INSERIR_CLIENTE", "ERROR_INSERIR_CLIENTE", e.getCause());

        } catch (HttpClientErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_INCLUIR_CLIENTE, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_CLIENTES);
            delegateExecution.setVariable("ERROR_TECNICO_INSERIR_CLIENTE", jsonException);
            throw new BpmnError("ERROR_INSERIR_CLIENTE", "ERROR_INSERIR_CLIENTE", e.getCause());

        } catch (HttpServerErrorException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            final String jsonException = ExceptionUtil.generateJsonFromException(e.getStatusCode().toString(),
                    MensagemDataSource.MessageDataSource.ERRO_INCLUIR_CLIENTE, e.getResponseBodyAsString(),
                    MensagemDataSource.Origem.SERVICE_CLIENTES);
            delegateExecution.setVariable("ERROR_TECNICO_INSERIR_CLIENTE", jsonException);
            throw new BpmnError("ERROR_INSERIR_CLIENTE", "ERROR_INSERIR_CLIENTE", e.getCause());

        } catch (Exception e) {
            final String jsonException = ExceptionUtil.generateJsonFromException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    MensagemDataSource.MessageDataSource.ERRO_INCLUIR_CLIENTE, e.getMessage(),
                    MensagemDataSource.Origem.SERVICE_CLIENTES);
            delegateExecution.setVariable("ERROR_TECNICO_INSERIR_CLIENTE", jsonException);
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            throw new BpmnError("ERROR_INSERIR_CLIENTE", "ERROR_INSERIR_CLIENTE", e.getCause());
        }




    }
}
