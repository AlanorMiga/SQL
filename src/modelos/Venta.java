package modelos;

import java.io.Serializable;

public class Venta implements Serializable{

	private String nombre;
	private String especie;
	private int edad;
	private String razon;
	private int id;
	
	public Venta() {
		
	}
	
	public Venta(String nombre, String especie, int edad, String razon) {
		this.nombre = nombre;
		this.especie = especie;
		this.edad = edad;
		this.razon = razon;
	}
	public Venta(int id, String nombre, String especie, int edad, String razon) {
		this.nombre = nombre;
		this.especie = especie;
		this.edad = edad;
		this.razon = razon;
		this.id= id;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	@Override
	public String toString() {
		return "Venta [nombre=" + nombre + ", especie=" + especie + ", edad=" + edad + ", razon=" + razon + "]";
	}

	
	

	
	
}
