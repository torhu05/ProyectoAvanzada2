package hn.uth.model;

import hn.uth.data.ClienteResponse;
import hn.uth.data.HabitacionResponse;
import hn.uth.data.ReservaResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface DatabaseRepository {
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/torhu90/sgh/cliente")
	Call<ClienteResponse> ConsultarCliente();
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/torhu90/sgh/habitacion")
	Call<HabitacionResponse> ConsultarHabitacion();
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/torhu90/sgh/reserva")
	Call<ReservaResponse> ConsultarReserva();
	

}



