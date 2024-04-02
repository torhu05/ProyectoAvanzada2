package hn.uth.data;
import java.util.List;

public class ClienteResponse {

	private List<Cliente> items;
	private int count;
	
	public List<Cliente> getItems() {
		return items;
	}
	public void setItems(List<Cliente> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
