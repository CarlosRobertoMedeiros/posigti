package br.com.igti.posgraduacao.config.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProcessVariables {
    public static final String EPPLUS_PROCESS_NAME = "negocio-abertura-conta";
    public static final String ID_IDEMPOTENTE = "idIdempotente";
    public static final String JSON_REQUSICAO_ABERTURA_CONTA = "jsonReqAberturaConta";
    public static final String JSON_RESP_ABERTURA_CONTA = "jsonRespAberturaConta";
    public static final String STATUS = "status";
}
