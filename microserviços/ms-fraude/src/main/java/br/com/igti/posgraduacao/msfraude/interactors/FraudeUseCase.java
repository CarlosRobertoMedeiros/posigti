package br.com.igti.posgraduacao.msfraude.interactors;

import br.com.igti.posgraduacao.msfraude.repositories.FraudeRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FraudeUseCase {


    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String TELEFONE_PATTERN = "^[0-9]{2}-([0-9]{8}|[0-9]{9})$";
    private static final String CEP_PATTERN = "^[0-9]{2}.[0-9]{3}-[0-9]{3}$";

    private static final Pattern patternEmail = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    private static final Pattern patternTelefone = Pattern.compile(TELEFONE_PATTERN, Pattern.CASE_INSENSITIVE);
    private static final Pattern patternCep = Pattern.compile(CEP_PATTERN, Pattern.CASE_INSENSITIVE);

    private FraudeRepository fraudeRepository;

    public FraudeUseCase(FraudeRepository fraudeRepository) {
        this.fraudeRepository = fraudeRepository;
    }

    public Boolean getEmailValido(String email) {
        Matcher matcher = patternEmail.matcher(email);
        if (!matcher.matches()){
            return Boolean.FALSE;
        }
        return fraudeRepository.getEmailValido(email);
    }


    public Boolean getTelefoneValido(String telefone) {
        Matcher matcher = patternTelefone.matcher(telefone);
        if (!matcher.matches()){
            return Boolean.FALSE;
        }
        return fraudeRepository.getTelefoneValido(telefone);
    }


    public Boolean getCepValido(String cep) {
        Matcher matcher = patternCep.matcher(cep);
        if (!matcher.matches()){
            return Boolean.FALSE;
        }
        return fraudeRepository.getCepValido(cep);
    }
}
