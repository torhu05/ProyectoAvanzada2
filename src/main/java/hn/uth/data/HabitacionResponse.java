package hn.uth.data;

import java.util.List;

public class HabitacionResponse {

	private List<SampleBook> items;
	private int count;
	
	public List<SampleBook> getItems() {
		return items;
	}
	public void setItems(List<SampleBook> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
