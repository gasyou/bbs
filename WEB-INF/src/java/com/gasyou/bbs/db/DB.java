package com.gasyou.bbs.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DB {

	private static DB instance = new DB();

	private List<String> messages = null;

	private DB() {
		this.reset();
	}

	public void addMessage(String msg) {
		messages.add(msg);
	}

	public List<String> getMessages() {
		return Collections.unmodifiableList(messages);
	}

	public void reset() {
		messages = new ArrayList<>();
	}

	public static DB getInstance() {
		return instance;
	}
}
