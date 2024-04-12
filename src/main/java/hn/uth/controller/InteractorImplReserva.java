package hn.uth.controller;

import hn.uth.data.Habitacion;
import hn.uth.data.Reserva;
import hn.uth.data.ReservaResponse;
import hn.uth.model.DatabaseRepositoryImpl;
import hn.uth.views.reserva.ViewModelReserva;
public class InteractorImplReserva implements InteractorReserva{

	private DatabaseRepositoryImpl modelo;
	private ViewModelReserva vista;
	
	public InteractorImplReserva(ViewModelReserva view) {
		super();
		this.vista = view;
		this.modelo = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 30000L);
	}

	@Override
	public void consultarReserva() {
		// TODO Auto-generated method stub
		try {
			ReservaResponse respuesta = this.modelo.consultarReserva();
			if(respuesta == null ||respuesta.getCount() == 0 || respuesta.getItems() == null) {
				this.vista.mostrarMensajeError("No hay Cliente que mostrar");
				
			}else {
				this.vista.mostrarReservaEnGrid(respuesta.getItems());
			}
			
		}catch (Exception error) {
			error.printStackTrace();
		}
		
	}
	@Override
	public void CrearReserva(Reserva nuevo) {
		try {
			boolean creado = this.modelo.CrearReserva(nuevo);
			if(creado == true) {
				this.vista.mostrarMensajeExito("Reserva creada exitosamente");
				
			}else {
				this.vista.mostrarMensajeError("Error al crear la habitacion");
			}
			
		}catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	@Override
	public void ActualizarReserva(Reserva cambiar) {
		try {
			boolean creado = this.modelo.ActualizarReserva(cambiar);
			if(creado == true) {
				this.vista.mostrarMensajeExito("Reserva modificada exitosamente");
				
			}else {
				this.vista.mostrarMensajeError("Error al modificar reserva");
			}
			
		}catch (Exception error) {
			error.printStackTrace();
		}
	}
}
