package Utils;

import java.util.List;

import Datos.Flight;

public class NotifyData {
	
	
	private NTYPE n;	//what
	private Object data;	//to who
	private Object data2;
	private List<Flight> l;
	public NotifyData(NTYPE n, Object data) {
		super();
		this.n = n;
		this.data = data;
	}
	public NotifyData(NTYPE n, Object data,Object data2) {
		super();
		this.n = n;
		this.data = data;
		this.data2 = data2;
	}

	public NTYPE getN() {
		return n;
	}
	public List<Flight> getList() {
		return l;
	}
	public void setN(NTYPE n) {
		this.n = n;
	}
	public Object getData() {
		return data;
	}
	public Object getData2() {
		return data2;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
