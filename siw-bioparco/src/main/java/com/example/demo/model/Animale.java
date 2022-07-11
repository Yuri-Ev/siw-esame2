package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Animale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank
	private String nome;

	@NotBlank
	@Size(max = 100)
	private String descrizione;
	
	private String photo;

	@NotNull
	@ManyToOne
	private Classe classe;

	private String areaGeografica;

	private String alimentazione;

	@NotNull
	@ManyToOne
	private Ambiente habitat;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getAreaGeografica() {
		return areaGeografica;
	}

	public void setAreaGeografica(String areaGeografica) {
		this.areaGeografica = areaGeografica;
	}

	public Ambiente getHabitat() {
		return habitat;
	}

	public void setHabitat(Ambiente habitat) {
		this.habitat = habitat;
	}

	public String getAlimentazione() {
		return alimentazione;
	}

	public void setAlimentazione(String alimentazione) {
		this.alimentazione = alimentazione;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
