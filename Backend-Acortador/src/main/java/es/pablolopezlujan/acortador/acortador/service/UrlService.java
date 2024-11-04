package es.pablolopezlujan.acortador.acortador.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.pablolopezlujan.acortador.acortador.model.Url;
import es.pablolopezlujan.acortador.acortador.repository.UrlRepository;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository urlRepository;
	
	// Variables que delimitan todos los caracteres que se pueden usar así como la longitud del URL
	private static final String CHAR_POOL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    
    // Método para crear un enlace corto
    public Url createShortenedUrl(String originalUrl) {
        String shortenedUrl = generateShortUrl();
        // Mientras el enlace corto exista vuelve a generar uno nuevo
        while (urlRepository.findByShortenedUrl(shortenedUrl).isPresent()) {
            shortenedUrl = generateShortUrl(); 
        }

        // Guardar datos Url original y Url acortada
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortenedUrl(shortenedUrl);
        return urlRepository.save(url);
    }
    
 // Método para recuperar la URL original a partir del enlace corto
    public Optional<String> getOriginalUrl(String shortenedUrl) {
        Optional<Url> url = urlRepository.findByShortenedUrl(shortenedUrl);
        return url.map(Url::getOriginalUrl);
    }
    
 // Generador de enlaces cortos
    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder(SHORT_URL_LENGTH);
        Random random = new Random();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortUrl.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }
        return shortUrl.toString();
    }

}
