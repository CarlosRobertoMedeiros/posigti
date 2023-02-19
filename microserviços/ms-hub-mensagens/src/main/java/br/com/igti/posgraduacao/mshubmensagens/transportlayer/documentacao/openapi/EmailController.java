package br.com.igti.posgraduacao.mshubmensagens.transportlayer.documentacao.openapi;

import br.com.igti.posgraduacao.mshubmensagens.transportlayer.input.EmailHubInput;
import br.com.igti.posgraduacao.mshubmensagens.transportlayer.output.EmailHubOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Email", description = "Operações de Envio de Email")
@RequestMapping(value = "/api/v1")
public interface EmailController {

    @Operation(summary = "enviar email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email enviado com sucesso",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EmailHubOutput.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @PostMapping(value = "/enviar-email", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<EmailHubOutput> enviarEmail(@Valid @RequestBody EmailHubInput emailHubInput);
}