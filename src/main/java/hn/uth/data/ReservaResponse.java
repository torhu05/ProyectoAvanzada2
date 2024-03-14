package hn.uth.data;

import java.util.List;

public class ReservaResponse {

	private List<SampleAddress> items;
	private int count;
	
	public List<SampleAddress> getItems() {
		return items;
	}
	public void setItems(List<SampleAddress> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
