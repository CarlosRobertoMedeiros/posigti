package br.com.igti.posgraduacao.datasources.feign.configuration.fraude;

import br.com.igti.posgraduacao.exception.feign.GatewayResourceIntegrationRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

public class ClientFraudeErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        try {
            InputStream body = response.body().asInputStream();

            Boolean responseFraudeData = objectMapper.readValue(body, Boolean.class);

            return new GatewayResourceIntegrationRuntimeException(
                    "Problema ao Consultar o Recurso de Fraude",
                    "Problema ao Consultar o Recurso de Fraude",
                    HttpStatus.NOT_FOUND,
                    "INTERNAL");

        } catch (IOException e) {
            return new GatewayResourceIntegrationRuntimeException(
                    "Problema ao Consultar o Recurso de Fraude",
                    "Problema ao Consultar o Recurso de Fraude",
                    HttpStatus.NOT_FOUND,
                    "INTERNAL");
        }
    }

}
