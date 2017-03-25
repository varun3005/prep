package org.vp.prep.dp.observer;

import java.util.ArrayList;
import java.util.List;

public class SubjectImpl implements Subject{
	
	private String id;
	private String message;
	private boolean isChanged;
	private List<Observer> observers;
	private final Object mutex = new Object();

	public SubjectImpl(String id) {
		super();
		this.id = id;
		observers = new ArrayList<>();
	}

	@Override
	public void subsribe(Observer o) {
		if(!observers.contains(o)){
			synchronized (mutex) {
				observers.add(o);
			}
		}
	}

	@Override
	public void unsubsribe(Observer o) {
		if(observers.contains(o)){
			synchronized (mutex) {
				observers.remove(o);
			}
		}
	}

	@Override
	public void notifySubsribers() {
		if(!isChanged){
			return;
		}
		synchronized (mutex) {
			for(Observer o : observers){
				o.getUpdate(this);
			}
		}
		
	}

	@Override
	public void updateData(String data) {
		message = data;
		isChanged = true;
		notifySubsribers();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SubjectImpl [id=" + id + ", message=" + message + "]";
	}
	
}
