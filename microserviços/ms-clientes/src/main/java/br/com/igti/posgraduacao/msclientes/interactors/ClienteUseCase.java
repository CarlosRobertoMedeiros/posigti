package br.com.igti.posgraduacao.msclientes.interactors;

import br.com.igti.posgraduacao.msclientes.entities.Cliente;
import br.com.igti.posgraduacao.msclientes.repositories.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ClienteUseCase {

    private final ClienteRepository clienteRepository;

    public ClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAll() {
        return this.clienteRepository.getAll();
    }

    public Cliente getClienteByCpf(String cpf) {
        Cliente clienteData = this.clienteRepository.getClienteByCpf(cpf);
        if (clienteData==null){
            new RuntimeException("Cadastro de cliente sem Dados");
        }
        return clienteData;
    }

    public Cliente inserirPositivo(Cliente cliente) {
        return this.clienteRepository.inserirCliente(cliente);
    }

    public Cliente getClienteById(String id) {
        Cliente clienteData = this.clienteRepository.getClienteById(id);
        if (clienteData==null){
            new RuntimeException("Cadastro de Cliente sem Dados");
        }
        return clienteData;
    }

    public Cliente atualizarCliente(Cliente cliente, String id) {
        Cliente clienteData = this.clienteRepository.getClienteById(id);

        if (clienteData!=null){
            Cliente clienteInformadoNoParametro =  clienteData;
            clienteInformadoNoParametro.setId(clienteData.getId());
            clienteInformadoNoParametro.setCpf(cliente.getCpf());
            clienteInformadoNoParametro.setBanco(cliente.getBanco());
            clienteInformadoNoParametro.setConta(cliente.getConta());
            clienteInformadoNoParametro.setNome(cliente.getNome());
            clienteInformadoNoParametro.setEndereco(cliente.getEndereco());
            clienteInformadoNoParametro.setTelefone(cliente.getTelefone());
            clienteInformadoNoParametro.setCep(cliente.getCep());
            clienteInformadoNoParametro.setRenda(cliente.getRenda());
            clienteInformadoNoParametro.setCartaoDebito(cliente.getCartaoDebito());
            clienteInformadoNoParametro.setEmprestimo(cliente.getEmprestimo());
            clienteInformadoNoParametro.setEmail(cliente.getEmail());
            clienteInformadoNoParametro.setDataInclusao(LocalDateTime.now());
            return this.clienteRepository.atualizarCliente(clienteInformadoNoParametro);
        }else{
            new RuntimeException("Não foi possível encontrar cadastros positivos para a atualização");
        }
        return null;
    }

    public ResponseEntity<Void> excluirCliente(String id) {
        Cliente clienteData = this.clienteRepository.getClienteById(id);

        if (clienteData!=null){
            this.clienteRepository.excluirCliente(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            new RuntimeException("Não foi possível encontrar clientes para a exclusão");
        }
        return null;
    }
}
