package hn.uth.data;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Reserva extends AbstractEntity {

	
    private String ticket;
    private double preciototal;
    private String idhabitacion;
    private String idcliente;
    private String fechainicio;
    private String fechafinal;
    
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public double getPreciototal() {
		return preciototal;
	}
	public void setPreciototal(double preciototal) {
		this.preciototal = preciototal;
	}
	public String getIdhabitacion() {
		return idhabitacion;
	}
	public void setIdhabitacion(String idhabitacion) {
		this.idhabitacion = idhabitacion;
	}
	public String getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}
	public String getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}
	public String getFechafinal() {
		return fechafinal;
	}
	public void setFechafinal(String fechafinal) {
		this.fechafinal = fechafinal;
	}
	
    
	
	
	
    
    
    

    
    

    
    
    
}
