package hn.uth.model;

import hn.uth.data.Cliente;
import hn.uth.data.ClienteResponse;
import hn.uth.data.HabitacionResponse;
import hn.uth.data.ReservaResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/torhu90/sgh/cliente")
	Call<ResponseBody> CrearCliente(@Body Cliente nuevo);

}



