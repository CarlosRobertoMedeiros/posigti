package br.com.igti.posgraduacao.msfraude.transportlayer.documentacao.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Tag(name = "Fraude", description = "Servicos de Verificacao de Fraude")
@RequestMapping(value = "/api/v1")
public interface FraudeController {

    @Operation(summary = "Validacao de email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fraude Nao Identificada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "400", description = "Fraude Identificada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping(value = "/fraude/email/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Valid
    ResponseEntity<Boolean> getEmailValido (
            @Parameter(name = "email", description = "Informe o email", required = true)
            @PathVariable("email") String email
    );

    @Operation(summary = "Validacao de telefone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fraude Nao Identificada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "400", description = "Fraude Identificada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping(value = "/fraude/telefone/{telefone}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Valid
    ResponseEntity<Boolean> getTelefoneValido (
            @Parameter(name = "telefone", description = "Informe o telefone", required = true)
            @PathVariable("telefone") String telefone
    );

    @Operation(summary = "Validacao de cep")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fraude Nao Identificada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "400", description = "Fraude Identificada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping(value = "/fraude/cep/{cep}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Valid
    ResponseEntity<Boolean> getCepValido (
            @Parameter(name = "cep", description = "Informe o cep", required = true)
            @PathVariable("cep") String cep
    );

}
