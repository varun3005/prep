package org.vp.prep.dp.observer;

import java.util.ArrayList;
import java.util.List;

public class TopicImpl implements Topic {

	private String id;
	private String message;
	private boolean isChanged;
	private List<Observer> observers;

	public TopicImpl(String id) {
		super();
		this.id = id;
		observers = new ArrayList<>();
	}

	@Override
	public synchronized void subsribe(Observer o) {
		if (!observers.contains(o)) {
			observers.add(o);
		}
	}

	@Override
	public synchronized void unsubsribe(Observer o) {
		if (observers.contains(o)) {
			observers.remove(o);
		}
	}

	@Override
	public void notifySubsribers() {
		if (!isChanged) {
			return;
		}
		for (Observer o : observers) {
			o.getUpdate(this);
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
		return "TopicImpl [id=" + id + ", message=" + message + "]";
	}

}
