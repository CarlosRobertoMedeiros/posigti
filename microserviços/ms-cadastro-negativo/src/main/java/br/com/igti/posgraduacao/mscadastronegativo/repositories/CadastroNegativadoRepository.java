package br.com.igti.posgraduacao.mscadastronegativo.repositories;

import br.com.igti.posgraduacao.mscadastronegativo.entities.Negativado;

import java.util.List;

public interface CadastroNegativadoRepository {

    List<Negativado> getAll();
    Negativado getByCpf(String cpf);
    Negativado post(Negativado negativado);
}
