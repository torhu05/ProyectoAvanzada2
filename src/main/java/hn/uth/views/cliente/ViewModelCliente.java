package hn.uth.views.cliente;

import java.util.List;

import hn.uth.data.SamplePerson;

public interface ViewModelCliente {

	void mostrarClientesEnGrid(List<SamplePerson> items);
	void mostrarMensajeError(String mensaje);
	
}
