package hn.uth.model;

import java.io.IOException;

import hn.uth.data.Cliente;
import hn.uth.data.ClienteResponse;
import hn.uth.data.Habitacion;
import hn.uth.data.HabitacionResponse;
import hn.uth.data.Reserva;
import hn.uth.data.ReservaResponse;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Call;

public class DatabaseRepositoryImpl {

	private static DatabaseRepositoryImpl INSTANCE;
	private DatabaseClient client;
	private DatabaseRepositoryImpl(String url, Long timeout) {
		client = new DatabaseClient(url, timeout);
		
	}
	
	public static DatabaseRepositoryImpl getInstance(String url, Long timeout) {
		if(INSTANCE == null) {
			synchronized(DatabaseRepositoryImpl.class) {
				if(INSTANCE == null) {
					INSTANCE = new DatabaseRepositoryImpl(url, timeout);
				}
			}
			
		}
		return INSTANCE;
	}
	
	public HabitacionResponse consultarHabitacion() throws IOException {
		Call<HabitacionResponse> call = client.getDB().ConsultarHabitacion();
		Response<HabitacionResponse> response = call.execute();
		if(response.isSuccessful()) {
			return response.body();
			
		}else {
			return null;
		}
	}
	public ClienteResponse consultarCliente() throws IOException {
		Call<ClienteResponse> call = client.getDB().ConsultarCliente();
		Response<ClienteResponse> response = call.execute();
		if(response.isSuccessful()) {
			return response.body();
			
		}else {
			return null;
		}
	}
	
	
	
	public ReservaResponse consultarReserva() throws IOException {
		Call<ReservaResponse> call = client.getDB().ConsultarReserva();
		Response<ReservaResponse> response = call.execute();
		if(response.isSuccessful()) {
			return response.body();
			
		}else {
			return null;
		}
	}
	
	public boolean CrearCliente(Cliente nuevo) throws IOException {
		Call<ResponseBody> call = client.getDB().CrearCliente(nuevo);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
	}
	
	public boolean CrearHabitacion(Habitacion nuevo) throws IOException {
		Call<ResponseBody> call = client.getDB().CrearHabitacion(nuevo);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
	}
	
	public boolean CrearReserva(Reserva nuevo) throws IOException {
		Call<ResponseBody> call = client.getDB().CrearReserva(nuevo);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
	}
	
	public boolean ActualizarCliente(Cliente cambiar) throws IOException {
		Call<ResponseBody> call = client.getDB().ActualizarCliente(cambiar);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
	}
	
	public boolean ActualizarHabitacion(Habitacion cambiar) throws IOException {
		Call<ResponseBody> call = client.getDB().ActualizarHabitacion(cambiar);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
	}
	
	public boolean ActualizarReserva(Reserva cambiar) throws IOException {
		Call<ResponseBody> call = client.getDB().ActualizarReserva(cambiar);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
	}
	
	public boolean EliminarCliente(String id) throws IOException {
		Call<ResponseBody> call = client.getDB().EliminarCliente(id);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
	}
	public boolean EliminarHabitacion(String id) throws IOException {
		Call<ResponseBody> call = client.getDB().EliminarHabitacion(id);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
	}
	public boolean EliminarReserva(String id) throws IOException {
		Call<ResponseBody> call = client.getDB().EliminarReserva(id);
		Response<ResponseBody> response = call.execute();
		return response.isSuccessful();
	}
}
