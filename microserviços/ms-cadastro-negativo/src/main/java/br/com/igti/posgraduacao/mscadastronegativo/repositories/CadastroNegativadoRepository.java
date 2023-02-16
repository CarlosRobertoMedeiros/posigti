package br.com.igti.posgraduacao.mscadastronegativo.repositories;

import br.com.igti.posgraduacao.mscadastronegativo.entities.Negativado;

import java.util.List;

public interface CadastroNegativadoRepository {

    List<Negativado> getAll();
    Negativado getNegativadoById(String id);
    Negativado getNegativadoByCpf(String cpf);
    Negativado inserirNegativado(Negativado negativado);
    Negativado atualizarNegativado(Negativado negativado);
    void excluirNegativado(String id);
}
