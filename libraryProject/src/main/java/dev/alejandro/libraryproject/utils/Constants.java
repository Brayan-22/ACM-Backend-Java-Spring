package dev.alejandro.libraryproject.utils;



public final class Constants {
    private Constants(){}

    public static class Message{
        public static final String START_SERVICE = "INICIO DE SERVICIO";
        public static final String REQUEST = "REQUEST -> \n";
        public static final String RESPONSE = "RESPONSE -> \n";
        public static final String FINISH_SERVICE = "FINALIZA SERVICIO";
        public static final String SUCCESS_OPERATION= "OPERACION EXITOSA";
        public static final String ERROR_OPERATION = "ERROR EN LA OPERACION";
        public static final String BAD_OPERATION = "OPERACION INVALIDA";

        public static final String JSON_ERROR = "ERROR EN LA CONVERSION A JSON";

        public static final String REGEX_NUMERIC = "SOLO SE ACEPTAN VALORES NUMERICOS";
        public static final String REGEX_ALPHANUMERIC = "SOLO SE ACEPTAN VALORES ALFANUMERICOS";
        public static final String REGEX_DATE = "FORMATO DE FECHA INVALIDO";
        public static final String REGEX_LETTERS = "SOLO SE ACEPTAN CARACTERES";
        public static final String REGEX_LETTERS_AND_SPACES = "SOLO SE ACEPTAN CARACTERES Y ESPACIOS";
        public static final String REGEX_ADDRESS = "FORMATO DE DIRECCION INVALIDO, VER DOCUMENTACION";

        private Message(){}
    }

    public static class Global{
        public static final String API_VERSION = "/v1";
        public static final String API_BASE_PATH = "/api";

        private Global(){}
    }
    public static class Libro{
        public static final String LIBRO_SERVICE_PATH = "/libro";
        public static final String LIBRO_SERVICE_PATH_TITLE = "/{title}";
        private Libro(){}
    }

    public static class Operation{
        public static final String OPERATION_REGISTER = "REGISTRO DE USUARIO";
        public static final String OPERATION_LOGIN = "AUTENTICACION DE USUARIO";
        private Operation(){}
    }
    public static class Formats{
        public static final String FORMAT_DATE_1 = "yyyyMMdd";
        public static final String FORMAT_DATE_2 = "yyyy-MM-dd:mm:ss.SSSSSS";
        public static final String FORMAT_DATE_3 = "yyyy-MM-dd";

        public static final String FORMAT_HOUR_1 = "HH:mm:ss";
        public static final String FORMAT_AMOUNT_1 = "'$'###,###,###";

        private Formats(){}
    }

}
