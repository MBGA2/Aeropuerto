package Observer;
import java.util.ArrayList;
import java.util.List;
import Controladores.NotifyData;

public class Observable {
	
	List<Observer> observers;
	
	public Observable()
	{
		this.observers = new ArrayList<Observer>();
	}
	
	public void notifyAllO(NotifyData d)	//id cam
	{
		for(Observer o : observers)
			o.update(d);
	}
	
	public void addObserver(Observer o)
	{
		observers.add(o);
	}
	
	public void removeObserver(Observer o)
	{
		observers.remove(o);
	}

}
