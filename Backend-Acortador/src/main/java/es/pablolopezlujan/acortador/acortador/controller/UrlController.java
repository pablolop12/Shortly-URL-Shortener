package es.pablolopezlujan.acortador.acortador.controller;

import java.util.Optional;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

import es.pablolopezlujan.acortador.acortador.model.Url;
import es.pablolopezlujan.acortador.acortador.service.UrlService;

@Controller
@RequestMapping("/api")
public class UrlController {
    
    @Autowired
    private UrlService urlService;

 // Endpoint para acortar una URL
    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> createShortenedUrl(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(json, new TypeReference<Map<String, String>>() {});

            String originalUrl = map.get("originalUrl");

            // Validar y corregir la URL si falta el prefijo
            if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
                originalUrl = "http://" + originalUrl; // Añadir "http://" por defecto
            }

            // Procesa la URL y genera el enlace acortado
            Url url = urlService.createShortenedUrl(originalUrl);

            Map<String, String> response = new HashMap<>();
            response.put("shortenedUrl", url.getShortenedUrl());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Invalid request format"), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para redirigir a la URL original basada en el shortenedUrl
    @GetMapping("/{shortenedUrl}")
    public RedirectView redirectToOriginalUrl(@PathVariable String shortenedUrl) {
        Optional<String> originalUrl = urlService.getOriginalUrl(shortenedUrl);

        if (originalUrl.isPresent()) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(originalUrl.get());
            redirectView.setStatusCode(HttpStatus.FOUND); // Código 302 para redirección
            redirectView.setExposeModelAttributes(false); // Para asegurar que no añade contexto adicional
            return redirectView;
        } else {
            RedirectView redirectView = new RedirectView("/error-page");
            redirectView.setStatusCode(HttpStatus.NOT_FOUND); // Código 404 si no se encuentra
            return redirectView;
        }
    }
}
