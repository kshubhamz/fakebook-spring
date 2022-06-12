package com.kshz.fakebookserver.websocket.core;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {
	private List<Observer<T>> observers = new ArrayList<>();
	
	public void subscribe(Observer<T> observer) {
		this.observers.add(observer);
	}
	
	protected void notifyObservers(T source, String username, String targetUser) {
		this.observers.forEach(observer -> observer.handle(new ChangeEvent<T>(source, username, targetUser)));
	}
}
