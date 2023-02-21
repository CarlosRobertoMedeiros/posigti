package br.com.igti.posgraduacao.datasources.tasks;


import br.com.igti.posgraduacao.config.util.OrquestradorProcessVariables;
import br.com.igti.posgraduacao.entities.OrquestradorSolicitarAberturaConta;
import br.com.igti.posgraduacao.exception.BussinessException;
import br.com.igti.posgraduacao.repositories.FraudeRepository;
import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskConsultarEmailProspecto implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(TaskConsultarEmailProspecto.class);
    private FraudeRepository fraudeRepository;

    public TaskConsultarEmailProspecto(FraudeRepository fraudeRepository) {
        this.fraudeRepository = fraudeRepository;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("TaskConsultarEmailProspecto - Inicio");

        Gson g = new Gson();
        OrquestradorSolicitarAberturaConta osac = g.fromJson(delegateExecution.getVariable(OrquestradorProcessVariables.JSON_REQ_ABERTURA_CONTA).toString(), OrquestradorSolicitarAberturaConta.class);

        final String email = osac.getEmail();
        if (email==null){
            throw new BussinessException();
        }
        final Boolean isEmailFraudado = fraudeRepository.isEmailFraudado(email);
        if (isEmailFraudado){
            throw new BussinessException();
        }
    }
}
