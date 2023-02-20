package br.com.igti.posgraduacao.datasources.tasks;


import br.com.igti.posgraduacao.config.util.OrquestradorProcessVariables;
import br.com.igti.posgraduacao.exception.BussinessException;
import br.com.igti.posgraduacao.repositories.FraudeRepository;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static br.com.igti.posgraduacao.config.util.OrquestradorProcessVariables.JSON_REQ_ABERTURA_CONTA;

@Component
@NoArgsConstructor
public class TaskConsultarCepProspecto implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(TaskConsultarCepProspecto.class);
    private FraudeRepository fraudeRepository;


    public TaskConsultarCepProspecto(FraudeRepository fraudeRepository) {
        this.fraudeRepository = fraudeRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("TaskConsultarCepProspecto - Inicio");
        final String cep = delegateExecution.getVariable(JSON_REQ_ABERTURA_CONTA).toString();
        if (cep==null){
            throw new BussinessException();
        }
        final Boolean isCepFraudado = fraudeRepository.isCepFraudado(cep);
        if (isCepFraudado){
            throw new BussinessException();
        }
    }
}
