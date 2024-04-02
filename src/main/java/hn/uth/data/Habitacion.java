package hn.uth.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;


@Entity
public class Habitacion extends AbstractEntity {

    @Lob
    @Column(length = 1000000)

    private String numerohabitacion;
    private String ocupacion; 
    private double precio;
    private String tipohabitacion;
    
    
	public String getNumerohabitacion() {
		return numerohabitacion;
	}
	public void setNumerohabitacion(String numerohabitacion) {
		this.numerohabitacion = numerohabitacion;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}


	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getTipohabitacion() {
		return tipohabitacion;
	}
	public void setTipohabitacion(String tipohabitacion) {
		this.tipohabitacion = tipohabitacion;
	}
   
}

    












