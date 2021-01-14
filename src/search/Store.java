package search;
import java.util.List;

public class Store<T> {
	T data;
	List<T> list;
	
	public Store(T data, List<T> list) {
		this.data = data;
		this.list = list;
	}
	
	public T getData() {
		return data;
	}
	
	public List<T> getList() {
		return list;
	}
}
