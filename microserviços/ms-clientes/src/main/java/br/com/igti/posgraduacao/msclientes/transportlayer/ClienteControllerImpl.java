package br.com.igti.posgraduacao.msclientes.transportlayer;

import br.com.igti.posgraduacao.msclientes.entities.Cliente;
import br.com.igti.posgraduacao.msclientes.exceptions.ExceptionUtil;
import br.com.igti.posgraduacao.msclientes.exceptions.ResourceException;
import br.com.igti.posgraduacao.msclientes.interactors.ClienteUseCase;
import br.com.igti.posgraduacao.msclientes.transportlayer.documentacao.openapi.ClienteController;
import br.com.igti.posgraduacao.msclientes.transportlayer.input.ClienteInput;
import br.com.igti.posgraduacao.msclientes.transportlayer.mappers.ClienteMapper;
import br.com.igti.posgraduacao.msclientes.transportlayer.output.ClienteOutput;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class ClienteControllerImpl implements ClienteController {

    private final ClienteUseCase clienteUseCase;

    public ClienteControllerImpl(ClienteUseCase clienteUseCase) {
        this.clienteUseCase = clienteUseCase;
    }

    @Override
    public ResponseEntity<List<ClienteOutput>> getAllClientes() {
        List<Cliente> clientes = null;
        try {
            clientes = clienteUseCase.getAll();
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(ClienteMapper.INSTANCE.mapListOutput(clientes));
    }
    @Override
    public ResponseEntity<ClienteOutput> getClienteById(
            @Parameter(name = "id", description = "Id da Transacao", required = true)
            @PathVariable("id") String id) {
        Cliente cliente = null;
        try {
            cliente = clienteUseCase.getClienteById(id);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(ClienteMapper.INSTANCE.mapOutput(cliente));
    }
    @Override
    public ResponseEntity<ClienteOutput> getClienteByCpf(
            @Parameter(name = "cpf", description = "Número do CPF do Cliente", required = true)
            @PathVariable("cpf") String cpf) {
        Cliente cliente = null;
        try {
            cliente = clienteUseCase.getClienteByCpf(cpf);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(ClienteMapper.INSTANCE.mapOutput(cliente));
    }

    @Override
    public ResponseEntity<ClienteOutput> inserirCliente(@RequestBody @Valid ClienteInput clienteInput) {
        Cliente cliente = null;
        try {
            cliente = clienteUseCase.inserirPositivo(ClienteMapper.INSTANCE.map(clienteInput));
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteMapper.INSTANCE.mapOutput(cliente));
    }

    @Override
    public ResponseEntity<ClienteOutput> atualizarCliente(@Parameter(name = "id", description = "Id da Transação", required = true)
                                                                @PathVariable("id") String id,
                                                                @Parameter(description = "Cliente para atualizacao", required = true, schema = @Schema(implementation = ClienteInput.class))
                                                                @Valid @RequestBody ClienteInput clienteInput) {

        Cliente cliente = null;
        try {
            cliente = clienteUseCase.atualizarCliente(ClienteMapper.INSTANCE.map(clienteInput), id);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(ClienteMapper.INSTANCE.mapOutput(cliente));
    }

    @Override
    public ResponseEntity excluirCliente(@PathVariable String id) {
        try {
            clienteUseCase.excluirCliente(id);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
