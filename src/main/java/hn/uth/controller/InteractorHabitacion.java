package hn.uth.controller;

import hn.uth.data.Cliente;
import hn.uth.data.Habitacion;

public interface InteractorHabitacion {

	void consultarHabitacion();
	void CrearHabitacion(Habitacion nuevo);
	void ActualizarHabitacion(Habitacion cambiar);
	void EliminarHabitacion(String id);
}
