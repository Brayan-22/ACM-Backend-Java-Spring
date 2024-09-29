package com.acm.acmweb.services;

import com.acm.acmweb.models.PeliculaDTO;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class PeliculaService {

    private Logger logger = LoggerFactory.getLogger(PeliculaService.class);

    @Value("${myapp.omdb.apikey}")
    private String apikey;


    private RestTemplate restTemplate;

    private HttpClient client;
    public PeliculaService(@Autowired HttpClient client, @Autowired RestTemplate restTemplate) {
        this.client = client;
        this.restTemplate = restTemplate;
    }

    public void getPeliculaByNameUsinRestTemplate(String nombre){
        StringBuilder str = new StringBuilder();
        str.append("http://www.omdbapi.com/");
        logger.info(apikey);
        str.append("?apikey="+apikey);
        str.append("&t="+nombre);
        PeliculaDTO p =restTemplate.getForObject(URI.create(str.toString()),PeliculaDTO.class);
        logger.info(p.toString());
    }

    public void getPeliculaByName(String nombre){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StringBuilder str = new StringBuilder();
        str.append("http://www.omdbapi.com/");
        logger.info(apikey);
        str.append("?apikey="+apikey);
        str.append("&t="+nombre);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(str.toString())).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info(response.body());
            PeliculaDTO p = gson.fromJson(response.body(),PeliculaDTO.class);
            mapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter(DefaultPrettyPrinter.DEFAULT_SEPARATORS));
            mapper.writeValue(new File("src/main/resources/pelicula.json"), p);
            logger.info(p.toString());
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
