package com.laboratorio.service.implementation;

import com.laboratorio.persistence.model.MateriaPrima;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsumoRequisisionApi {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://18.116.23.243:8000";

    public ConsumoRequisisionApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void enviarProducto(MateriaPrima materiaPrima) {
        String token = autenticarYObtenerToken();
        if (token == null) {
            System.err.println("No se pudo obtener el token");
            return;
        }

        String urlProducto = BASE_URL + "/api/producto";

        Map<String, Object> body = new HashMap<>();
        body.put("nombre", materiaPrima.getReferencia());
        body.put("tipo", materiaPrima.getUnidad());
        body.put("cantidad", 500);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token); // Agrega "Authorization: Bearer <token>"

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(urlProducto, request, String.class);
            System.out.println("Código de respuesta: " + response.getStatusCode());
            System.out.println("Respuesta: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error al enviar el producto: " + e.getMessage());
        }
    }

    private String autenticarYObtenerToken() {
        String urlLogin = BASE_URL + "/api/auth/login";

        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", "laboratory@gmail.com");
        loginRequest.put("password", "laboratory123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(loginRequest, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(urlLogin, request, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return (String) response.getBody().get("token");
            }
        } catch (Exception e) {
            System.err.println("Error al autenticar: " + e.getMessage());
        }

        return null;
    }
}
