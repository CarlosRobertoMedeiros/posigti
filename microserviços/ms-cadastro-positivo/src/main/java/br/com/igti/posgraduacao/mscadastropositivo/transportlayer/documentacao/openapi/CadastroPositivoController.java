package br.com.igti.posgraduacao.mscadastropositivo.transportlayer.documentacao.openapi;

import br.com.igti.posgraduacao.mscadastropositivo.transportlayer.input.PositivoInput;
import br.com.igti.posgraduacao.mscadastropositivo.transportlayer.output.PositivoOutput;
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

@Tag(name = "Cadastro Positivo", description = "Operações relacionadas ao Cadastro Positivo")
@RequestMapping(value = "/api/v1")
public interface CadastroPositivoController {

    @Operation(summary = "Obtém todos os cadastros positivos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastros positivos encontrados",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PositivoInput.class))}),
            @ApiResponse(responseCode = "400", description = "Cadastros positivos nao encontrados",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping(value = "/positivos/", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<PositivoOutput>> getAllPositivos();

    @Operation(summary = "Obtém cadastro positivo pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro positivo encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PositivoInput.class))}),
            @ApiResponse(responseCode = "400", description = "Cadastro positivo não encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping (value = "/positivos/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PositivoOutput> getPositivoById(
            @Parameter(name = "id", description = "Id da Transacao", required = true)
            @PathVariable("id") String id
    );

    @Operation(summary = "Obtém cadastro positivo pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro positivo encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PositivoInput.class))}),
            @ApiResponse(responseCode = "400", description = "Cadastro positivo não encontrado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping (value = "/positivos/cpf/{cpf}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PositivoOutput> getPositivoByCpf(
            @Parameter(name = "cpf", description = "Número do CPF para cadastro positivo", required = true)
            @PathVariable("cpf") String cpf
    );



    @Operation(summary = "Insere informação no cadastro positivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro positivo incluido",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PositivoOutput.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @PostMapping(value = "/positivos", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PositivoOutput> inserirPositivo(@Valid @RequestBody PositivoInput negativadoInput);



    @Operation(summary = "Atualiza informação no cadastro positivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro positivo atualizado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PositivoOutput.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @PutMapping(value = "/positivos/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PositivoOutput> atualizarPositivo(@Parameter(name = "id", description = "Número do CPF para cadastro positivo", required = true)
                                                         @PathVariable("id") String id,
                                                         @Parameter(description = "Cadastro Positivo para atualizacao", required = true, schema = @Schema(implementation = PositivoInput.class))
                                                         @Valid @RequestBody PositivoInput positivoInput);



    @Operation(summary = "Exclui registro do cadastro positivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Negativacao Excluida",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PositivoOutput.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @DeleteMapping(path = "/positivos/{id}")
    ResponseEntity<Void> excluirPositivo(@Parameter(name = "id", description = "Id da Transacao", required = true)
                                           @PathVariable("id") String id);









}
