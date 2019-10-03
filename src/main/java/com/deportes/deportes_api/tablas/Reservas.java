package com.deportes.deportes_api.tablas;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
public class Reservas {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;
 
 @Pattern(regexp = ".*([0-9]{2}$)",message="El Carnet debe contener el numero con una longitud mínima de 4 caracteres")
 private String carnet;
 
 @Pattern(regexp = ".*([a-zA-ZáéíóúÁÉÍÓÚ]{2}$)",message="El nombre debe contener letras y numeros con una longitud mínima de 2 caracteres")
 private String nombre;
 
 @Pattern(regexp = ".*([a-zA-ZáéíóúÁÉÍÓÚ]{2}$)",message="El nombre debe contener letras y numeros con una longitud mínima de 2 caracteres")
 private String apellido;

 @Pattern(regexp = ".*([a-zA-Z0-9áéíóúÁÉÍÓÚ]{2}$)",message="La dirección debe contener letras y numeros con una longitud mínima de 5 caracteres")
 private String dependencia;

 @Pattern(regexp = ".*([a-zA-ZáéíóúÁÉÍÓÚ]{2}$)",message="La Facultad debe contener letras y numeros con una longitud mínima de 2 caracteres")
 private String jubilado;
 

 private Integer descuento;
 
 
 
 
 
 //GET Y SET
public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getCarnet() {
	return carnet;
}

public void setCarnet(String carne) {
	this.carnet = carne;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getApellido() {
	return apellido;
}

public void setApellido(String apellido) {
	this.apellido = apellido;
}

public String getDependencia() {
	return dependencia;
}

public void setDependencia(String dependencia) {
	this.dependencia = dependencia;
}
 
public String getJubilado() {
	return jubilado;
}

public void setJubilado(String jubilado) {
	this.jubilado = jubilado;
} 


public Integer getDescuento() {
	return descuento;
}

public void setDescuento(Integer descuento) {
	this.descuento = descuento;
}

}