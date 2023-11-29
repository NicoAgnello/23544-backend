package ar.com.codoacodo;

import java.time.LocalDate;

public class Auto {
	
	/*Atributos*/
	
	private String marca;
	
	private String modelo;
	
	private String color;
	
	private Integer año;
	
	private Float velocidad;
	
	private Float velocidadMax;
	
	private Boolean encendido;
	
	private String dominio;
	
	private LocalDate fechaCreacion;
	
	/*Constructor*/
	
	public Auto (String marca, String modelo, String color, Integer año, String dominio, Float velocidadMax  ) {
		this.encendido = Boolean.FALSE;
		this.velocidad = 0f;
		this.fechaCreacion = LocalDate.now();
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.año = año;
		this.dominio = dominio;
		this.velocidadMax = velocidadMax;
	}
	
	/*Metodos*/
	
	public void encender () {
		if(!this.encendido) {
			this.encendido = Boolean.TRUE;
		} else {
			System.out.println("Ya esta encendido!!");
		}
	}
	
	public void apagar () {
		if(this.encendido) {
			this.encendido = Boolean.FALSE;
			velocidad = 0f;
		} else {
			System.out.println("Ya estaba apagado!!");
		}
	}
	
	public void acelerar () {
		if(encendido) {
			if(velocidad < velocidadMax) {				
				velocidad ++;
			} else {
				System.out.println("Velocidad maxima: " + velocidad + " alcanzada.");
			}
		} else {
			System.out.println("Primero debe encender el auto para acelerar.");
		}
	}
	
	public void frenar () {
		if(encendido) {
			if(velocidad > 0) {
				velocidad -- ;	
			}else {
				System.out.println("La velocidad llego a 0.");
			}
		} else {
			System.out.println("Frenando con el auto apagado.");
		}
	}
}
