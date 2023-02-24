package br.com.igti.posgraduacao.datasources.feign.configuration.cliente;

import br.com.igti.posgraduacao.exception.feign.GatewayResourceIntegrationRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

public class ClientNotificaClienteErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        try {
            InputStream body = response.body().asInputStream();

            Boolean responseFraudeData = objectMapper.readValue(body, Boolean.class);

            return new GatewayResourceIntegrationRuntimeException(
                    "Problema ao Notificar o Cliente por email",
                    "Problema ao Notificar o Cliente por email",
                    HttpStatus.NOT_FOUND,
                    "INTERNAL");

        } catch (IOException e) {
            return new GatewayResourceIntegrationRuntimeException(
                    "Problema ao Notificar o Cliente por email",
                    "Problema ao Notificar o Cliente por email",
                    HttpStatus.NOT_FOUND,
                    "INTERNAL");
        }
    }
}
