package br.com.igti.posgraduacao.datasources;

import br.com.igti.posgraduacao.config.util.EngineUtil;
import br.com.igti.posgraduacao.exception.ExceptionUtil;
import br.com.igti.posgraduacao.exception.ResourceException;
import br.com.igti.posgraduacao.repositories.OrquestradorRepository;
import br.com.igti.posgraduacao.transportlayer.input.OrquestradorRequestDto;
import br.com.igti.posgraduacao.transportlayer.output.OrquestradorResponseDto;
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

import static br.com.igti.posgraduacao.config.util.ProcessVariables.*;

@Component

public class OrquestradorDataSource implements OrquestradorRepository {

    private static final Logger log = LoggerFactory.getLogger(OrquestradorDataSource.class);

    @Override
    public OrquestradorResponseDto solicitarAberturaDeConta(OrquestradorRequestDto orquestradorRequestDto) {
        final VariableMap variables = Variables.createVariables();
        final ObjectMapper mapper = new ObjectMapper();
        ProcessInstanceWithVariables instance;
        String IdempotentIdGenerated = UUID.randomUUID().toString();
        try{
            String jsonReqAberturaConta = mapper.writeValueAsString(orquestradorRequestDto).replace("\"","\'");
            variables.putValue(ID_IDEMPOTENTE, IdempotentIdGenerated);
            variables.putValue(JSON_REQUSICAO_ABERTURA_CONTA,  Variables.objectValue(jsonReqAberturaConta));

            instance = EngineUtil.getInstance().getRuntimeEngine().createProcessInstanceByKey(ABERTURA_DE_CONTA_PROCESS_NAME)
                    .businessKey(IdempotentIdGenerated)
                    .setVariables(variables)
                    .executeWithVariablesInReturn();

        }catch (JsonProcessingException e) {
            log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
            ResourceException resourceException =  ExceptionUtil.generateException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    MensagemDataSource.MessageDataSource.ERRO_JSON_EXCEPTION, e.getMessage(),
                    MensagemDataSource.Origem.SERVICE_PRE_APROVADO_QUENTE);
            throw resourceException;
        }

        lancaExceptionEmCasoDeErroNoProcesso(instance.getVariables().entrySet().stream().filter(erro -> erro.getKey().contains("ERROR_TECNICO")).collect(Collectors.toUnmodifiableList()));

        return preencheResponse(instance.getProcessInstanceId(),
                                instance.getVariables().get(JSON_RESP_ABERTURA_CONTA),
                                IdempotentIdGenerated,
                                orquestradorRequestDto.getCpf());
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
                        MensagemDataSource.Origem.SERVICE_PRE_APROVADO_QUENTE);
                throw jsonException;
            }
        }
    }

    private OrquestradorResponseDto preencheResponse(String processInstanceId, Object respostaPreAprovado, String idIdempotente, String cpf) throws ResourceException {
        final OrquestradorResponseDto responseDto = new OrquestradorResponseDto();

//        RetornoConsultaLimite retornoConsultaLimite = null;
//        responseDto.setIdProcesso(processInstanceId);
//
//        if(null != respostaPreAprovado) {
//
//            responseDto.setIdIdempotente(idIdempotente);
//            responseDto.setCpf(cpf);
//            responseDto.setCodSisParceiro(codSisParceiro);
//
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                ResponseConsultaLimite responseConsultaLimite = mapper.readValue(String.valueOf(respostaPreAprovado), ResponseConsultaLimite.class);
//                retornoConsultaLimite = responseConsultaLimite.getResults();
//                responseDto.setValorMinimoPreAprovado(BigDecimal.valueOf(retornoConsultaLimite.getLimitePreAprovadoDto().get(0).getValorMinimoPreAprovado()));
//                responseDto.setValorMaximoPreAprovado(BigDecimal.valueOf(retornoConsultaLimite.getLimitePreAprovadoDto().get(0).getValorMaximoPreAprovado()));
//                responseDto.setDatasPagamentoDto(retornoConsultaLimite.getLimitePreAprovadoDto().get(0).getDatasPagamentoDto());
//            } catch (JsonMappingException e) {
//                log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
//                ResourceException resourceException =  ExceptionUtil.generateException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
//                        MensagemDataSource.MessageDataSource.ERRO_JSON_EXCEPTION, e.getMessage(),
//                        MensagemDataSource.Origem.SERVICE_PRE_APROVADO_QUENTE);
//                throw resourceException;
//            } catch (JsonProcessingException e) {
//                log.error(MensagemDataSource.Erro.LOG, e.getMessage(), e.getCause(), e.getStackTrace());
//                ResourceException resourceException =  ExceptionUtil.generateException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
//                        MensagemDataSource.MessageDataSource.ERRO_JSON_EXCEPTION, e.getMessage(),
//                        MensagemDataSource.Origem.SERVICE_PRE_APROVADO_QUENTE);
//                throw resourceException;
//            }
//        } else {
//            log.error(MensagemDataSource.Erro.LOG, "Dados não recuperados.", "Dados respostaPreAprovado is null", "Dados respostaPreAprovado is null");
//            ResourceException resourceException =  ExceptionUtil.generateException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
//                    MensagemDataSource.MessageDataSource.ERRO_JSON_EXCEPTION, "Dados respostaPreAprovado is null",
//                    MensagemDataSource.Origem.SERVICE_PRE_APROVADO_QUENTE);
//            throw resourceException;
//        }

        return responseDto;
    }




}
