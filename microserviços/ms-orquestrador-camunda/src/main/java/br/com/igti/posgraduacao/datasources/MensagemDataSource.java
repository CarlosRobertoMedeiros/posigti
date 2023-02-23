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
        public static final String SERVICE_CADASTRO_POSITIVO = "SERVICE-CADASTRO-POSITIVO";
        public static final String SERVICE_CADASTRO_NEGATIVO = "SERVICE-CADASTRO-NEGATIVO";
        public static final String SERVICE_CLIENTES = "SERVICE-CLIENTES";
        public static final String SERVICE_HUB_MENSAGENS = "SERVICE-HUB-MENSAGENS";

    }

    public static class MessageDataSource {

        private MessageDataSource() {
        }

        public static final String ERRO_JSON_EXCEPTION = "Erro ao efetuar uma operação com JSON";
        public static final String INTERNAL_ERROR_EXCEPTION = "ERRO AO EXECUTAR UMA OPERAÇÃO";
        public static final String ERRO_INESPERADO_ABERTURA_CONTA = "Erro inesperado durante ao processo de Abertura de Conta";
        public static final String ERRO_CONSULTA_FRAUDE_CEP = "Erro ao consultar o cep";
        public static final String ERRO_CONSULTA_FRAUDE_EMAIL = "Erro ao consultar o email";
        public static final String ERRO_CONSULTA_FRAUDE_TELEFONE = "Erro ao consultar o telefone";

        public static final String ERRO_INCLUIR_PROSPECTO_CADASTRO_POSITIVO = "Erro ao incluir o prospecto no cadastro positivo";
        public static final String ERRO_INCLUIR_PROSPECTO_CADASTRO_NEGATIVO = "Erro ao incluir o prospecto no cadastro negativo";
        public static final String ERRO_INCLUIR_CLIENTE = "Erro ao incluir um cliente";


        public static final String JSON_ERROR_EXCEPTION = "ERRO AO EFETUAR UMA OPERAÇÃO COM JSON";
//
//        public static final String ERROR_TECNICO_FRAUDE_TELEFONE_PROSPECTO = "ERROR_TECNICO_FRAUDE_TELEFONE_PROSPECTO";
//        public static final String ERROR_TECNICO_FRAUDE_CEP_PROSPECTO = "ERROR_TECNICO_FRAUDE_CEP_PROSPECTO";
//        public static final String ERROR_TECNICO_FRAUDE_EMAIL_PROSPECTO = "ERROR_TECNICO_FRAUDE_EMAIL_PROSPECTO";


    }
}
