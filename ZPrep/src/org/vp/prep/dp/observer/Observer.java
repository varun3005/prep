package org.vp.prep.dp.observer;

public interface Observer {
	public void setSubject(Subject s);
	public void getUpdate(Subject s);
}
