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

@Component
public class TaskConsultarTelefoneProspecto implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(TaskConsultarTelefoneProspecto.class);
    private FraudeRepository fraudeRepository;

    public TaskConsultarTelefoneProspecto(FraudeRepository fraudeRepository) {
        this.fraudeRepository = fraudeRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("TaskConsultarTelefoneProspecto - Inicio");
        final String telefone = delegateExecution.getVariable(OrquestradorProcessVariables.TELEFONE).toString();
        if (telefone==null){
            throw new BussinessException();
        }
        final Boolean isTelefoneFraudado = fraudeRepository.isTelefoneFraudado(telefone);
        if (isTelefoneFraudado){
            throw new BussinessException();
        }
    }
}
