package hn.uth.data;

import jakarta.persistence.Entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
public class SamplePerson extends AbstractEntity {
	
	private String identidad;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String fechacumpleanos;
    private String sexo;
    private String nacionalidad;
    private String lugarprocedencia;
  		
	
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
	public String getFechaCumpleaños() {
		return fechacumpleanos;
	}
	public void setFechaCumpleaños(String fechaCumpleaños) {
		this.fechacumpleanos = fechaCumpleaños;
	}
	    
	public String getIdentidad() {
		return identidad;
	}
	public void setIdentidad(String identidad) {
		this.identidad = identidad;
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
	public String getLugarProcedencia() {
		return lugarprocedencia;
	}
	public void setLugarProcedencia(String lugarProcedencia) {
		this.lugarprocedencia = lugarProcedencia;
	}
	
}
