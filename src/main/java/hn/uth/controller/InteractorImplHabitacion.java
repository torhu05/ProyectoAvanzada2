package hn.uth.controller;

import hn.uth.data.Cliente;
import hn.uth.data.Habitacion;
import hn.uth.data.HabitacionResponse;
import hn.uth.model.DatabaseRepositoryImpl;
import hn.uth.views.habitacion.ViewModelHabitacion;

public class InteractorImplHabitacion implements InteractorHabitacion{

	private DatabaseRepositoryImpl modelo;
	private ViewModelHabitacion vista;
	
	public InteractorImplHabitacion(ViewModelHabitacion view) {
		super();
		this.vista = view;
		this.modelo = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 30000L);
	}

	@Override
	public void consultarHabitacion() {
		// TODO Auto-generated method stub
		try {
			HabitacionResponse respuesta = this.modelo.consultarHabitacion();
			if(respuesta == null ||respuesta.getCount() == 0 || respuesta.getItems() == null) {
				this.vista.mostrarMensajeError("No hay Cliente que mostrar");
				
			}else {
				this.vista.mostrarHabitacionEnGrid(respuesta.getItems());;
			}
			
		}catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	@Override
	public void CrearHabitacion(Habitacion nuevo) {
		try {
			boolean creado = this.modelo.CrearHabitacion(nuevo);
			if(creado == true) {
				this.vista.mostrarMensajeExito("Habitacion creada exitosamente");
				
			}else {
				this.vista.mostrarMensajeError("Error al crear la habitacion");
			}
			
		}catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	@Override
	public void ActualizarHabitacion(Habitacion cambiar) {
		try {
			boolean creado = this.modelo.ActualizarHabitacion(cambiar);
			if(creado == true) {
				this.vista.mostrarMensajeExito("Habitacion modificada exitosamente");
				
			}else {
				this.vista.mostrarMensajeError("Error al modificar la habitacion");
			}
			
		}catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	@Override
	public void EliminarHabitacion(String id) {
		try {
			boolean Eliminado = this.modelo.EliminarHabitacion(id);
			if(Eliminado == true) {
				this.vista.mostrarMensajeExito("Habitacion Borrada exitosamente");
				
			}else {
				this.vista.mostrarMensajeError("Error al Borrar la Habitacion");
			}
			
		}catch (Exception error) {
			error.printStackTrace();
		}
	}

	
}
