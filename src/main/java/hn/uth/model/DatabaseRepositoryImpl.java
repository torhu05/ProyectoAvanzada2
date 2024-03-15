package hn.uth.model;

import java.io.IOException;

import hn.uth.data.ClienteResponse;
import hn.uth.data.HabitacionResponse;
import hn.uth.data.ReservaResponse;
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
}
