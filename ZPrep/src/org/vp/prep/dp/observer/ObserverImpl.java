package org.vp.prep.dp.observer;

public class ObserverImpl implements Observer{

	private String id;
	private Topic topic;
	
	public ObserverImpl(String id) {
		super();
		this.id = id;
	}

	@Override
	public void getUpdate(Topic topic) {
		System.out.println("Recieved update on "+this.id+" for topic:"+topic);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
}
