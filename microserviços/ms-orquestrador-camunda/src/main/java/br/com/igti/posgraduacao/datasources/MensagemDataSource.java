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

        public static final String FORMALIZACAO_GATEWAY = "FORMALIZACAO-GATEWAY";
        public static final String GESTOR_LIMITE_GATEWAY = "GESTOR-DE-LIMITE-GATEWAY";
        public static final String PARAMETROS_GATEWAY = "PARAMETROS-GATEWAY";
        public static final String TERMO_GATEWAY_AWS = "TERMO-GATEWAY";
        public static final String CLI360_GATEWAY = "CLI360-GATEWAY";
        public static final String FUNCAO_GATEWAY = "FUNCAO-GATEWAY";
        public static final String CONTRATACAO_USECASE = "SERVICE-CREDITO-PESSOAL-CONTRATACAO";
        public static final String QUESTIONARIO_REPOSITORY = "QUESTIONARIO_REPOSITORY";
        public static final String DYNAMODB_GATEWAY = "DYNAMODB-GATEWAY";
        public static final String OFERTA_CREDITO_REPOSITORY = "OFERTA_CREDITO_REPOSITORY";
        public static final String NOVO_CALCULO_REPOSITORY = "NOVO_CALCULO_REPOSITORY";
        public static final String SERVICE_BANCOS = "Service Bancos";
        public static final String SERVICE_CREDITO_PESSOAL = "SERVICE-CREDITO-PESSOAL";
        public static final String SERVICE_PRE_APROVADO_QUENTE = "SERVICE-PRE-APROVADO-QUENTE";
    }

    public static class MessageDataSource {

        private MessageDataSource() {
        }

        public static final String ERRO_JSON_EXCEPTION = "Erro ao efetuar uma operação com JSON";
        public static final String ERRO_INESPERADO_ABERTURA_CONTA = "Erro inesperado durante ao processo de Abertura de Conta";
    }
}
