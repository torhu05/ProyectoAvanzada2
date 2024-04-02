package hn.uth.data;

import java.util.List;

public class ReservaResponse {

	private List<Reserva> items;
	private int count;
	
	public List<Reserva> getItems() {
		return items;
	}
	public void setItems(List<Reserva> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
