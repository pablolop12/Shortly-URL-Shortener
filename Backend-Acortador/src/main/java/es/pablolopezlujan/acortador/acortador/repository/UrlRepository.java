package es.pablolopezlujan.acortador.acortador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.pablolopezlujan.acortador.acortador.model.Url;


public interface UrlRepository extends JpaRepository <Url, Long> {
	Optional<Url> findByShortenedUrl(String shortenedUrl);
	
}

