package br.com.igti.posgraduacao.datasources;

import br.com.igti.posgraduacao.config.util.EngineUtil;
import br.com.igti.posgraduacao.entities.OrquestradorRespostaSolicitarAberturaConta;
import br.com.igti.posgraduacao.entities.OrquestradorSolicitarAberturaConta;
import br.com.igti.posgraduacao.entities.Process;
import br.com.igti.posgraduacao.exception.ExceptionUtil;
import br.com.igti.posgraduacao.exception.ResourceException;
import br.com.igti.posgraduacao.repositories.OrquestradorSolicitarAberturaContaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.com.igti.posgraduacao.config.util.OrquestradorProcessVariables.*;

@Component
public class OrquestradorSolicitarAberturaContaDataSource implements OrquestradorSolicitarAberturaContaRepository {

    private static final Logger log = LoggerFactory.getLogger(OrquestradorSolicitarAberturaContaDataSource.class);

    @Override
    public OrquestradorRespostaSolicitarAberturaConta solicitarAberturaDeConta(OrquestradorSolicitarAberturaConta orquestradorSolicitarAberturaConta) {
        final VariableMap variables = Variables.createVariables();
        final ObjectMapper mapper = new ObjectMapper();
        ProcessInstanceWithVariables instance;
        String bussinessKey = UUID.randomUUID().toString();
        try{
            String jsonReqAberturaConta = mapper.writeValueAsString(orquestradorSolicitarAberturaConta).replace("\"","\'");
            variables.putValue(BUSSINESS_KEY, bussinessKey.toString());
            variables.putValue(JSON_REQ_ABERTURA_CONTA,  Variables.objectValue(jsonReqAberturaConta));
            variables.putValue(DEBITO_CARTAO,  orquestradorSolicitarAberturaConta.getCartaoDebito());
            variables.putValue(EMPRESTIMO,  orquestradorSolicitarAberturaConta.getEmprestimo());
            variables.putValue(RENDA,  orquestradorSolicitarAberturaConta.getRenda());

            instance = EngineUtil.getInstance().getRuntimeEngine().createProcessInstanceByKey(ABERTURA_DE_CONTA_PROCESS_NAME)
                    .businessKey(bussinessKey.toString())
                    .setVariables(variables)
                    .executeWithVariablesInReturn();

            System.out.println(variables);



        }catch (Exception e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            ResourceException resourceException =  ExceptionUtil.generateException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    MensagemDataSource.MessageDataSource.INTERNAL_ERROR_EXCEPTION, e.getMessage(),
                    MensagemDataSource.Origem.SERVICE_ABERTURA_DE_CONTA);
            throw resourceException;
        }

        lancaExceptionEmCasoDeErroNoProcesso(instance.getVariables().entrySet().stream().filter(erro -> erro.getKey().contains("ERROR_TECNICO")).collect(Collectors.toUnmodifiableList()));

        return preencheResponse(instance.getProcessInstanceId(),
                                instance.getVariables().get(BUSSINESS_KEY).toString(),
                                orquestradorSolicitarAberturaConta);
    }

    private void lancaExceptionEmCasoDeErroNoProcesso(List<Map.Entry<String, Object>> errorList) throws ResourceException {
        for (Map.Entry<String, Object> entry: errorList) {
            ResourceException resourceException;
            try {
                resourceException = ExceptionUtil.generateExceptionFormJson(entry.getValue().toString());
                throw resourceException;
            } catch (JsonProcessingException e) {
                ResourceException jsonException =  ExceptionUtil.generateException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                        MensagemDataSource.MessageDataSource.ERRO_JSON_EXCEPTION, e.getMessage(),
                        MensagemDataSource.Origem.SERVICE_ABERTURA_DE_CONTA);
                throw jsonException;
            }
        }
    }

    private OrquestradorRespostaSolicitarAberturaConta preencheResponse(String processInstanceId, String bussinessKey, OrquestradorSolicitarAberturaConta orquestradorSolicitarAberturaConta) throws ResourceException {

        var orquestradorRespostaSolicitarAberturaConta = new OrquestradorRespostaSolicitarAberturaConta();
        var process = new Process();

        if(null != orquestradorRespostaSolicitarAberturaConta) {

            process.setProcessId(processInstanceId);
            process.setBussinessKey(bussinessKey);

            try{
                orquestradorRespostaSolicitarAberturaConta.setProcess(process);
                orquestradorRespostaSolicitarAberturaConta.setCpf(orquestradorSolicitarAberturaConta.getCpf());
                orquestradorRespostaSolicitarAberturaConta.setNome(orquestradorSolicitarAberturaConta.getNome());
                orquestradorRespostaSolicitarAberturaConta.setEndereco(orquestradorSolicitarAberturaConta.getEndereco());
                orquestradorRespostaSolicitarAberturaConta.setDdd(orquestradorSolicitarAberturaConta.getDdd());
                orquestradorRespostaSolicitarAberturaConta.setTelefone(orquestradorSolicitarAberturaConta.getTelefone());
                orquestradorRespostaSolicitarAberturaConta.setEmail(orquestradorSolicitarAberturaConta.getEmail());
                orquestradorRespostaSolicitarAberturaConta.setCep(orquestradorSolicitarAberturaConta.getCep());
                orquestradorRespostaSolicitarAberturaConta.setRenda(orquestradorSolicitarAberturaConta.getRenda());
                orquestradorRespostaSolicitarAberturaConta.setCartaoDebito(orquestradorSolicitarAberturaConta.getCartaoDebito());
                orquestradorRespostaSolicitarAberturaConta.setEmprestimo(orquestradorSolicitarAberturaConta.getEmprestimo());
            }catch (Exception e){
                log.error(MensagemDataSource.Erro.LOG, "Dados não recuperados.", "Dados orquestradorSolicitarAberturaConta is null", "Dados orquestradorSolicitarAberturaConta is null");
                ResourceException resourceException =  ExceptionUtil.generateException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                        MensagemDataSource.MessageDataSource.ERRO_JSON_EXCEPTION, "Dados orquestradorSolicitarAberturaConta is null",
                        MensagemDataSource.Origem.SERVICE_ABERTURA_DE_CONTA);
                throw resourceException;
            }
        }
        return orquestradorRespostaSolicitarAberturaConta;
    }




}
