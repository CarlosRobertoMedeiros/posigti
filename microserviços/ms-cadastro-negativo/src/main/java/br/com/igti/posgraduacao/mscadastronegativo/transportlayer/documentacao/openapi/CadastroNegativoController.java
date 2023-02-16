package br.com.igti.posgraduacao.mscadastronegativo.transportlayer.documentacao.openapi;

import br.com.igti.posgraduacao.mscadastronegativo.transportlayer.input.NegativadoInput;
import br.com.igti.posgraduacao.mscadastronegativo.transportlayer.output.NegativadoOutput;
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

@Tag(name = "Cadastro Negativo", description = "Operações relacionadas ao Cadastro Negativo")
@RequestMapping(value = "/api/v1")
public interface CadastroNegativoController {

    @Operation(summary = "Obter todos os Cadastrados Negativados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Negativados encontrados",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NegativadoInput.class))}),
            @ApiResponse(responseCode = "400", description = "Negativados não encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping(value = "/negativados/", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<NegativadoOutput>> getAllNegativados();

    @Operation(summary = "Obter cadastro negativado pelo seu cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Negativados encontrados",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NegativadoInput.class))}),
            @ApiResponse(responseCode = "400", description = "Negativados não encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping (value = "/negativados/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<NegativadoOutput> getNegativadoById(
            @Parameter(name = "id", description = "Id da Transacao", required = true)
            @PathVariable("id") String id
    );

    @Operation(summary = "Obter cadastro negativado pelo seu cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Negativados encontrados",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NegativadoInput.class))}),
            @ApiResponse(responseCode = "400", description = "Negativados não encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping (value = "/negativados/cpf/{cpf}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<NegativadoOutput> getNegativadoByCpf(
            @Parameter(name = "cpf", description = "Número do CPF para negativacao", required = true)
            @PathVariable("cpf") String cpf
    );



    @Operation(summary = "Negativar o registro perante a instituicao")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Negativado incluido",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NegativadoOutput.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @PostMapping(value = "/negativados", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<NegativadoOutput> inserirNegativado(@Valid @RequestBody NegativadoInput negativadoInput);



    @Operation(summary = "Atualiza a negativacao do registro perante a instituicao")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Negativacao Atualizada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NegativadoOutput.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @PutMapping(value = "/negativados/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<NegativadoOutput> atualizarNegativado(@Parameter(name = "id", description = "Número do CPF para negativacao", required = true)
                                                         @PathVariable("id") String id,
                                                         @Parameter(description = "Negativado para atualizacao", required = true, schema = @Schema(implementation = NegativadoInput.class))
                                                         @Valid @RequestBody NegativadoInput negativadoInput);



    @Operation(summary = "Exclui a negativacao do registro perante a instituicao")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Negativacao Excluida",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NegativadoOutput.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @DeleteMapping(path = "/negativados/{id}")
    ResponseEntity<Void> excluirNegativado(@Parameter(name = "id", description = "Id da Transacao", required = true)
                                           @PathVariable("id") String id);









}
