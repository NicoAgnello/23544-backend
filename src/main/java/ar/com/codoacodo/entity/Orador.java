package ar.com.codoacodo.entity;

import java.time.LocalDate;

public class Orador {
	
	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private String tema;
	private LocalDate fechaAlta;
	
	


	//Constructor
	//el primero lo uso cuando mando un objeto a la db;
	public Orador(String nombre, String apellido, String email, String tema, LocalDate fechaAlta) {
		init(nombre, apellido, email, tema, fechaAlta);
		//alt + shift + m
	}

	
	//El segundo lo utilizamos para traer un objeto desde la bd;
	public Orador(Long id, String nombre, String apellido, String email, String tema, LocalDate fechaAlta) {	
		this.id = id;
		init(nombre, apellido, email, tema, fechaAlta);
	}
	
	
	private void init(String nombre, String apellido, String email, String tema, LocalDate fechaAlta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.tema = tema;
		this.fechaAlta = fechaAlta;
	}


	//Implementar un metodo llamado toString() para ver mas lindo en la consola los atributos;
	//alt shift s
	// otra forma de polimorfismo: sobreescritura => un metodo que existe en una clase base (java.lang.Object) pero su hijo (Orador) lo cambia.
	
	//@Override => No es necesario.
	
	public String toString() {
		return "Orador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", tema="
				+ tema + ", fechaAlta=" + fechaAlta + "]";
	}

	
	//Getters y Setters

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		if(nombre != null) {
			
			this.nombre = nombre;
		} else {
			this.nombre = "N/D";
		}
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTema() {
		return tema;
	}


	public void setTema(String tema) {
		this.tema = tema;
	}


	public LocalDate getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	
	
	
	
	
}
