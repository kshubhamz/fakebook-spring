package com.kshz.fakebookserver.websocket.core;

public interface Observer<T> {
	public void handle(ChangeEvent<T> args);
}
