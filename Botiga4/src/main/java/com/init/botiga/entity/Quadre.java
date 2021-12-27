package com.init.botiga.entity;


import java.io.Serializable;
import java.sql.Date;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="quadre")
public class Quadre implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_quadre")
	private int id_quadre;
	
	@Column(name="nom_quadre",nullable = false, length = 30)
	private String nomQuadre;
	
	@Column(name="autor_quadre",nullable = false, length = 30)
	private String autorQuadre;
	
	@Column(name="preu",nullable = false)
	private double preu;
	
	@Column(name="date")
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date fecha;
	
	//@Column(name= "botiga_id", nullable = false)
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name= "botiga_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Botiga botiga;

	public Quadre() {
	}

	public Quadre(String nomQuadre, String autorQuadre, double preu,java.util.Date date, Botiga botiga) {
		this.nomQuadre = nomQuadre;
		this.autorQuadre = autorQuadre;
		this.preu = preu;
		this.fecha = date;
		this.botiga = botiga;
	}

	public int getId_quadre() {
		return id_quadre;
	}

	public void setId_quadre(int id_quadre) {
		this.id_quadre = id_quadre;
	}

	public String getNomQuadre() {
		return nomQuadre;
	}

	public void setNomQuadre(String nomQuadre) {
		this.nomQuadre = nomQuadre;
	}

	public String getAutorQuadre() {
		return autorQuadre;
	}

	public void setAutorQuadre(String autorQuadre) {
		this.autorQuadre = autorQuadre;
	}

	public double getPreu() {
		return preu;
	}

	public void setPreu(double preu) {
		this.preu = preu;
	}


	public java.util.Date getFecha() {
		return fecha;
	}

	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}

	public Botiga getBotiga() {
		return botiga;
	}

	public void setBotiga(Botiga botiga) {
		this.botiga= botiga;
	}
	
	
	
}
