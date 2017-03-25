package org.vp.prep.dp.observer;

public interface Subject {

	public void subsribe(Observer o);
	public void unsubsribe(Observer o);
	public void notifySubsribers();
	public void updateData(String data);
}
