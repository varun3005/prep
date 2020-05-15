package org.vp.prep.dp.observer;

public class TestPattern {

	public static void main(String[] args) {
		Topic maths = new TopicImpl("Maths");
		Observer o1 = new ObserverImpl("Observer1");
		Observer o2 = new ObserverImpl("Observer2");
		Observer o3 = new ObserverImpl("Observer3");
		
		o1.setTopic(maths);
		o2.setTopic(maths);
		o3.setTopic(maths);
		
		maths.updateData("New Data 1");
		maths.subsribe(o1);
		maths.subsribe(o2);
		maths.subsribe(o3);
		
		maths.updateData("New Data 2");
		
		maths.unsubsribe(o3);
		
		maths.updateData("New Data 3");
		
	}
}
