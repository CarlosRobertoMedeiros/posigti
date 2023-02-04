package br.com.igti.posgraduacao.msclientes.transportlayer;

import br.com.igti.posgraduacao.msclientes.transportlayer.documentacao.openapi.ClientesController;
import br.com.igti.posgraduacao.msclientes.transportlayer.input.ClienteRequisicaoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientesControllerImpl implements ClientesController {

    @Override
    public ResponseEntity<ClienteRequisicaoDto> get(long cpf) {
        return new ResponseEntity<ClienteRequisicaoDto>(HttpStatus.NOT_IMPLEMENTED);
    }
}
