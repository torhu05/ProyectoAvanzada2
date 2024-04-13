package hn.uth.model;

import hn.uth.data.Cliente;
import hn.uth.data.ClienteResponse;
import hn.uth.data.Habitacion;
import hn.uth.data.HabitacionResponse;
import hn.uth.data.Reserva;
import hn.uth.data.ReservaResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/torhu90/sgh/habitacion")
	Call<ResponseBody> CrearHabitacion(@Body Habitacion nuevo);
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/torhu90/sgh/reserva")
	Call<ResponseBody> CrearReserva(@Body Reserva nuevo);
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/torhu90/sgh/cliente")
	Call<ResponseBody> ActualizarCliente(@Body Cliente cambiar);
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/torhu90/sgh/habitacion")
	Call<ResponseBody> ActualizarHabitacion(@Body Habitacion cambiar);
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/torhu90/sgh/reserva")
	Call<ResponseBody> ActualizarReserva(@Body Reserva cambiar);
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/torhu90/sgh/cliente")
	Call<ResponseBody> EliminarCliente(@Query("identidad") String identidad);
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/torhu90/sgh/habitacion")	
	Call<ResponseBody> EliminarHabitacion(@Query("numerohabitacion") String numerohabitacion);
	
	@Headers({
		"Accept: application/json",
		"User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/torhu90/sgh/reserva")
	Call<ResponseBody> EliminarReserva(@Query("ticket") String ticket);

}



