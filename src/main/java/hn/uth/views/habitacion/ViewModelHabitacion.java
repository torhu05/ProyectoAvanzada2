package hn.uth.views.habitacion;

import java.util.List;

import hn.uth.data.SampleBook;

public interface ViewModelHabitacion {

	void mostrarHabitacionEnGrid(List<SampleBook> items);
	void mostrarMensajeError(String mensaje);
}
