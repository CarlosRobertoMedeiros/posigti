package br.com.igti.posgraduacao.msclientes.transportlayer.documentacao.openapi;

import br.com.igti.posgraduacao.msclientes.transportlayer.input.ClienteInput;
import br.com.igti.posgraduacao.msclientes.transportlayer.output.ClienteOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Clientes", description = "Operações de Clientes")
@RequestMapping(value = "/api/v1")
public interface ClienteController {

    @Operation(summary = "Obtém todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ClienteInput.class))}),
            @ApiResponse(responseCode = "400", description = "Clientes nao encontrados",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping(value = "/clientes/", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<ClienteOutput>> getAllClientes();

    @Operation(summary = "Obtém cliente pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ClienteInput.class))}),
            @ApiResponse(responseCode = "400", description = "cliente não encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping (value = "/clientes/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ClienteOutput> getClienteById(
            @Parameter(name = "id", description = "Id da Transacao", required = true)
            @PathVariable("id") String id
    );

    @Operation(summary = "Obtém cliente pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ClienteInput.class))}),
            @ApiResponse(responseCode = "400", description = "Cadastro não encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping (value = "/clientes/cpf/{cpf}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ClienteOutput> getClienteByCpf(
            @Parameter(name = "cpf", description = "Número do CPF para cadastro de cliente", required = true)
            @PathVariable("cpf") String cpf
    );



    @Operation(summary = "insere informações do Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro de cliente incluido",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ClienteOutput.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @PostMapping(value = "/clientes", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ClienteOutput> inserirCliente(@Valid @RequestBody ClienteInput clienteInput);



    @Operation(summary = "Atualiza informações do cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro de cliente",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ClienteOutput.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @PutMapping(value = "/clientes/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ClienteOutput> atualizarCliente(@Parameter(name = "id", description = "ID do Cliente para atualização", required = true)
                                                         @PathVariable("id") String id,
                                                         @Parameter(description = "Cadastro de cliente para atualizacao", required = true, schema = @Schema(implementation = ClienteInput.class))
                                                         @Valid @RequestBody ClienteInput clienteInput);



    @Operation(summary = "Exclui informações do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente excluido",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ClienteOutput.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @DeleteMapping(path = "/clientes/{id}")
    ResponseEntity<Void> excluirCliente(@Parameter(name = "id", description = "Id da Transacao", required = true)
                                           @PathVariable("id") String id);









}
