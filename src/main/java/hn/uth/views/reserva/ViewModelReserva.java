package hn.uth.views.reserva;

import java.util.List;

import hn.uth.data.SampleAddress;

public interface ViewModelReserva {

	void mostrarReservaEnGrid(List<SampleAddress> items);
	void mostrarMensajeError(String mensaje);
}
