package es.pablolopezlujan.acortador.acortador.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework .web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

import es.pablolopezlujan.acortador.acortador.model.Url;
import es.pablolopezlujan.acortador.acortador.service.UrlService;

@Controller
@RequestMapping("/api")
public class UrlController {
	
	@Autowired // Inyectar Servicio de Url
    private UrlService urlService;
	
	// Endpoint para acortar una URL
	@PostMapping("/shorten")
	public ResponseEntity<String> createShortenedUrl(@RequestBody String json) {
	    try {
	        // Convertir JSON a Map usando TypeReference para seguridad de tipos
	        ObjectMapper mapper = new ObjectMapper();
	        Map<String, String> map = mapper.readValue(json, new TypeReference<Map<String, String>>() {});

	        String originalUrl = map.get("originalUrl"); // Extrae solo el valor de la URL

	        // Procesa la URL como texto plano
	        Url url = urlService.createShortenedUrl(originalUrl);
	        return new ResponseEntity<>(url.getShortenedUrl(), HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Invalid request format", HttpStatus.BAD_REQUEST);
	    }
	}

   
    // Endpoint para redirigir a la URL original
    @GetMapping("/{shortenedUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortenedUrl) {
        Optional<String> originalUrl = urlService.getOriginalUrl(shortenedUrl);

        if (originalUrl.isPresent()) {
            // Devuelve la URL original en el cuerpo de la respuesta
            return ResponseEntity.ok(originalUrl.get());
        } else {
            // Devuelve un 404 si el enlace corto no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("URL not found");
        }
    }



}
