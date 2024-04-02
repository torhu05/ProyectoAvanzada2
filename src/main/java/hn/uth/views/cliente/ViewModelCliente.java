package hn.uth.views.cliente;

import java.util.List;

import hn.uth.data.Cliente;

public interface ViewModelCliente {

	void mostrarClientesEnGrid(List<Cliente> items);
	void mostrarMensajeError(String mensaje);
	
}
