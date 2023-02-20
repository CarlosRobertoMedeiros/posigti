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
    }

    public static class MessageDataSource {

        private MessageDataSource() {
        }

        public static final String ERRO_JSON_EXCEPTION = "Erro ao efetuar uma operação com JSON";
        public static final String INTERNAL_ERROR_EXCEPTION = "ERRO AO EXECUTAR UMA OPERAÇÃO";
        public static final String ERRO_INESPERADO_ABERTURA_CONTA = "Erro inesperado durante ao processo de Abertura de Conta";
    }
}
