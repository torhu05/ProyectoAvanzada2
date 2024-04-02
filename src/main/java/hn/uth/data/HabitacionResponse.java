package hn.uth.data;

import java.util.List;

public class HabitacionResponse {

	private List<Habitacion> items;
	private int count;
	
	public List<Habitacion> getItems() {
		return items;
	}
	public void setItems(List<Habitacion> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
