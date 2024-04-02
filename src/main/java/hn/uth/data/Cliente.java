package hn.uth.data;

import jakarta.persistence.Entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
public class Cliente extends AbstractEntity {
	
	private String identidad;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String fechacumpleanos;
    private String sexo;
    private String nacionalidad;
    private String lugarprocedencia;
    
	public String getIdentidad() {
		return identidad;
	}
	public void setIdentidad(String identidad) {
		this.identidad = identidad;
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFechacumpleanos() {
		return fechacumpleanos;
	}
	public void setFechacumpleanos(String fechacumpleanos) {
		this.fechacumpleanos = fechacumpleanos;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getLugarprocedencia() {
		return lugarprocedencia;
	}
	public void setLugarprocedencia(String lugarprocedencia) {
		this.lugarprocedencia = lugarprocedencia;
	}
}
		
	
    