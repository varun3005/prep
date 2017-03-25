package org.vp.prep.dp.observer;

public class ObserverImpl implements Observer{

	private String id;
	private Subject subject;
	
	public ObserverImpl(String id) {
		super();
		this.id = id;
	}

	@Override
	public void getUpdate(Subject s) {
		System.out.println("Recieved update on "+this.id+" for subject:"+s);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}
