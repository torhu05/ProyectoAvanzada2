package hn.uth.controller;

import hn.uth.data.Habitacion;
import hn.uth.data.Reserva;

public interface InteractorReserva {

	void consultarReserva();
	void CrearReserva(Reserva nuevo);
	void ActualizarReserva(Reserva cambiar);
}
