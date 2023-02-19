package br.com.igti.posgraduacao.msclientes.transportlayer.mappers;

import br.com.igti.posgraduacao.msclientes.entities.Cliente;
import br.com.igti.posgraduacao.msclientes.transportlayer.input.ClienteInput;
import br.com.igti.posgraduacao.msclientes.transportlayer.output.ClienteOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente map(ClienteInput clienteInput);

    ClienteOutput mapOutput(Cliente cliente);

    default List<ClienteOutput> mapListOutput(List<Cliente> clientes){
        final List<ClienteOutput> clienteOutputList = new ArrayList<>();
        for (Cliente cliente: clientes) {
            ClienteOutput clienteOutput = ClienteMapper.INSTANCE.mapOutput(cliente);
            clienteOutputList.add(clienteOutput);
        }
        return clienteOutputList;
    }


}
