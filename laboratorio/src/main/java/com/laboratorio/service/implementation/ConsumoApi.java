package com.laboratorio.service.implementation;

import com.laboratorio.dto.DespachoProductoDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsumoApi {

    private final RestTemplate restTemplate;
    private final String URL_DESPACHO = "http://ec2-3-140-254-107.us-east-2.compute.amazonaws.com";

    public ConsumoApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void enviarDespacho(DespachoProductoDTO despacho) {
        String token = autenticarYObtenerToken();
        if (token == null) {
            System.err.println("No se pudo obtener el token");
            return;
        }

        String urlDespacho = URL_DESPACHO + "/api/laboratorio-despachos/";

        Map<String, Object> body = new HashMap<>();
        body.put("external_order_id", despacho.external_order_id());
        body.put("product_name", despacho.product_name());
        body.put("quantity", despacho.quantity());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(urlDespacho, request, String.class);
            System.out.println("Código de respuesta: " + response.getStatusCode());
            System.out.println("Respuesta: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error al enviar el despacho: " + e.getMessage());
        }
    }

    private String autenticarYObtenerToken() {
        String urlLogin = URL_DESPACHO + "/auth/login/";

        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("username", "laboratory_service");
        loginRequest.put("password", "laboratory123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(loginRequest, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(urlLogin, request, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return (String) response.getBody().get("access");
            }
        } catch (Exception e) {
            System.err.println("Error al autenticar: " + e.getMessage());
        }

        return null;
    }

}
