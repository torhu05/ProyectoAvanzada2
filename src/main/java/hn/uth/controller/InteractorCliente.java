package hn.uth.controller;

import hn.uth.data.Cliente;

public interface InteractorCliente {

	void consultarCliente();
	void CrearCliente(Cliente nuevo);
	void ActualizarCliente(Cliente cambiar);
	void EliminarCliente(String id);
}
