package br.com.igti.posgraduacao.mscadastropositivo.repositories;

import br.com.igti.posgraduacao.mscadastropositivo.entities.Positivo;

import java.util.List;

public interface CadastroPositivoRepository {

    List<Positivo> getAll();
    Positivo getPositivoById(String id);
    Positivo getPositivoByCpf(String cpf);
    Positivo inserirPositivo(Positivo negativado);
    Positivo atualizarPositivo(Positivo negativado);
    void excluirPositivo(String id);
}
