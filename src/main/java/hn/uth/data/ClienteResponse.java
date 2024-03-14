package hn.uth.data;
import java.util.List;

public class ClienteResponse {

	private List<SamplePerson> items;
	private int count;
	
	public List<SamplePerson> getItems() {
		return items;
	}
	public void setItems(List<SamplePerson> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
