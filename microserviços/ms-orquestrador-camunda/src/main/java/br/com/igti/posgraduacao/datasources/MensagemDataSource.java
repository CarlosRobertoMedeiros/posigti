package br.com.igti.posgraduacao.datasources;

public class MensagemDataSource {

    private MensagemDataSource(){
    }

    public static class Erro {
        private Erro(){
        }

        public static final String LOG = " Erro messsage : {} , cause: {}, stacktrace : {}";
    }

    public static class Origem {
        private Origem(){
        }
        public static final String SERVICE_ABERTURA_DE_CONTA = "SERVICE-ABERTURA-DE-CONTA";
        public static final String SERVICE_FRAUDE = "SERVICE-FRAUDE";
    }

    public static class MessageDataSource {

        private MessageDataSource() {
        }

        public static final String ERRO_JSON_EXCEPTION = "Erro ao efetuar uma operação com JSON";
        public static final String INTERNAL_ERROR_EXCEPTION = "ERRO AO EXECUTAR UMA OPERAÇÃO";
        public static final String ERRO_INESPERADO_ABERTURA_CONTA = "Erro inesperado durante ao processo de Abertura de Conta";
        public static final String ERRO_CONSULTA_CLIENTE = "Erro ao consultar o cliente";
        public static final String ERRO_CONSULTA_IMOVEL = "Erro ao consultar o imóvel";
        public static final String ERRO_CONSULTA_AUTOMOVEL = "Erro ao consultar o automóvel";
        public static final String JSON_ERROR_EXCEPTION = "ERRO AO EFETUAR UMA OPERAÇÃO COM JSON";

        public static final String ERROR_TECNICO_TELEFONE_PROSPECTO = "ERROR_TECNICO_TELEFONE_PROSPECTO";


    }
}
