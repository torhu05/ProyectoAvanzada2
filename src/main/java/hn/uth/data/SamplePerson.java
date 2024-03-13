package hn.uth.data;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;

@Entity
public class SamplePerson extends AbstractEntity {

	private String identidad;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private LocalDate fechaCumpleaños;
    private String sexo;
    private String nacionalidad;
    private String lugarProcedencia;
  		
	
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
	public LocalDate getFechaCumpleaños() {
		return fechaCumpleaños;
	}
	public void setFechaCumpleaños(LocalDate fechaCumpleaños) {
		this.fechaCumpleaños = fechaCumpleaños;
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
		return lugarProcedencia;
	}
	public void setLugarProcedencia(String lugarProcedencia) {
		this.lugarProcedencia = lugarProcedencia;
	}
	
}
