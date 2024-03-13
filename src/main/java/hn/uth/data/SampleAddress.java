package hn.uth.data;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class SampleAddress extends AbstractEntity {

	
    private String ticket;
    private double precioTotal;
    private String idHabitacion;
    private String idCliente;
    private LocalDate fecInicio;
    private LocalDate fecFinal;
    
    
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public String getIdHabitacion() {
		return idHabitacion;
	}
	public void setIdHabitacion(String idHabitacion) {
		this.idHabitacion = idHabitacion;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public LocalDate getFecInicio() {
		return fecInicio;
	}
	public void setFecInicio(LocalDate fecInicio) {
		this.fecInicio = fecInicio;
	}
	public LocalDate getFecFinal() {
		return fecFinal;
	}
	public void setFecFinal(LocalDate fecFinal) {
		this.fecFinal = fecFinal;
	}
	
    
    
    

    
    

    
    
    
}
