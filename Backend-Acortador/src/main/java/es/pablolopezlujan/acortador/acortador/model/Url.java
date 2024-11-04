package es.pablolopezlujan.acortador.acortador.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

/*
 * Modelo para la tabla Urls de la base de datos
 * Campos: Id, URL original, URL Acortada, Fecha de creacion, Contador de clicks
 */
@Entity
@Table (name = "urls")
public class Url {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@Column(name = "original_url", nullable = false)
	private String originalUrl;
			
	@Column(name = "shortened_url", nullable = false, unique = true)
	private String shortenedUrl;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	
	@PrePersist//Asigna la fecha de creción cuando se guarda la entidad por primera vez
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

	//GETTERS Y SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortenedUrl() {
		return shortenedUrl;
	}

	public void setShortenedUrl(String shortenedUrl) {
		this.shortenedUrl = shortenedUrl;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	 
	 
	 
	 

}
