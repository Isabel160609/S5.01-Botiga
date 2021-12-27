package com.init.botiga.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="botiga")
public class Botiga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_botiga")
	private int id_botiga;
	
	@Column(name="nom_botiga",nullable = false, length = 30)
	private String  nomBotiga;
	
	@Column(name="num_maxquadres",nullable = false)
	private int numMaxquadres;
	
	@OneToMany(mappedBy="botiga", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Quadre> llistaQuadres;

	
	
	public Botiga() {
	}


	public Botiga(String nomBotiga, int numMaxquadres) {
		this.nomBotiga = nomBotiga;
		this.numMaxquadres = numMaxquadres;
		
	}


	public int getId() {
		return id_botiga;
	}


	public void setId(int id) {
		this.id_botiga = id;
	}


	public String getNomBotiga() {
		return nomBotiga;
	}


	public void setNomBotiga(String nomBotiga) {
		this.nomBotiga = nomBotiga;
	}

	

	public int getNumMaxquadres() {
		return numMaxquadres;
	}


	public void setNumMaxquadres(int numMaxquadres) {
		this.numMaxquadres = numMaxquadres;
	}


	public List<Quadre> getLlistaQuadres() {
		return llistaQuadres;
	}


	public void setLlistaQuadres(List<Quadre> llistaQuadres) {
		this.llistaQuadres = llistaQuadres;
	}


	public void addQuadre(Quadre elQuadre) {
		
if(llistaQuadres==null) llistaQuadres=new ArrayList<>();
		
		if (llistaQuadres.size()<numMaxquadres) {
		
		llistaQuadres.add(elQuadre);
		
		elQuadre.setBotiga(this);
		
		}
	}
	
	public void CremaTotsElsQuadres() {
		this.llistaQuadres.removeAll(llistaQuadres);
	}
}
