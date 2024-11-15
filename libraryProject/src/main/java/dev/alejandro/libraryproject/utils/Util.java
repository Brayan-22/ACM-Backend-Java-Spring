package dev.alejandro.libraryproject.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class Util {

    private Util(){}
    public static JsonNode getJsonNode(URI baseUri, Map<String,String> params){
        RestTemplate template = new RestTemplateBuilder().rootUri(baseUri.toString()).build();
        JsonNode response = null;
        try {
            response = template.getForObject(baseUri, JsonNode.class);
            //Su propia logica para recuperar Json de otras api rest :)
            log.info("JSON: {}",response);
        }catch (Exception e){
            log.error(Constants.Message.JSON_ERROR,e);
        }
        return response;
    }

    public static String getJson(Object object){
        String response = null;
        try {
            response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
            log.info("JSON: {}",response);
        }catch (Exception e){
            log.error(Constants.Message.JSON_ERROR,e);
        }
        return response;
    }

    public static String getJsonObfuscate(Object object,String... obfuscate){
        String response = null;
        try{
            response = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
            for (String element : obfuscate){
                response = response.replace(element,"**********");
            }
        }catch (Exception e){
            log.error(Constants.Message.JSON_ERROR,e);
        }
        return response;
    }

    public static List<String> fieldsValidator(BindingResult result){
        List<String> errors = result.getFieldErrors().stream()
                .map(err -> "El campo "+err.getField() + " : "+err.getDefaultMessage())
                .toList();
        log.info("ERRORS: {}",Util.getJson(errors));
        return errors;
    }

    public static Boolean validateStringField(String field){
        Boolean response = Boolean.TRUE;
        if (Objects.isNull(field) || field.isBlank()) {
            response = Boolean.FALSE;
        }
        return response;
    }
}
