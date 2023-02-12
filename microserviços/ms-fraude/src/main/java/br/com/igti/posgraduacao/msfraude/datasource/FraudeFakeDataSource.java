package br.com.igti.posgraduacao.msfraude.datasource;

import br.com.igti.posgraduacao.msfraude.repositories.FraudeRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FraudeFakeDataSource implements FraudeRepository {

    List<FraudeDadosMock> dadosMockados = Arrays.asList(
            new FraudeDadosMock("usuario1@gmail.com","79-22743390","91.550-715"),
            new FraudeDadosMock("usuario2@gmail.com","62-27513197","57.080-500"),
            new FraudeDadosMock("usuario3@gmail.com","91-31254746","68.904-385")
    );

    @Override
    public Boolean getEmailValido(String email) {
        return dadosMockados.stream()
                .filter(linha -> linha.getEmail().equals(email))
                .count() >0;
    }

    @Override
    public Boolean getTelefoneValido(String telefone) {
        return dadosMockados.stream()
                .filter(linha -> linha.getTelefone().equals(telefone))
                .count() >0;
    }

    @Override
    public Boolean getCepValido(String cep) {
        return dadosMockados.stream()
                .filter(linha -> linha.getCep().equals(cep))
                .count() >0;



    }
}
