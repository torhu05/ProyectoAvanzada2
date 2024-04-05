package hn.uth.views.habitacion;

import java.util.List;

import hn.uth.data.Habitacion;

public interface ViewModelHabitacion {

	void mostrarHabitacionEnGrid(List<Habitacion> items);
	void mostrarMensajeError(String mensaje);
	void mostrarMensajeExito(String mensaje);
}
