package hn.uth.controller;

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

	
}
