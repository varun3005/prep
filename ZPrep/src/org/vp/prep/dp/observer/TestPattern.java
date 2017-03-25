package org.vp.prep.dp.observer;

public class TestPattern {

	public static void main(String[] args) {
		Subject subject = new SubjectImpl("Maths");
		Observer o1 = new ObserverImpl("Observer1");
		Observer o2 = new ObserverImpl("Observer2");
		Observer o3 = new ObserverImpl("Observer3");
		
		o1.setSubject(subject);
		o2.setSubject(subject);
		o3.setSubject(subject);
		
		subject.updateData("New Data 1");
		subject.subsribe(o1);
		subject.subsribe(o2);
		subject.subsribe(o3);
		
		subject.updateData("New Data 2");
		
		subject.unsubsribe(o3);
		
		subject.updateData("New Data 3");
		
	}
}
