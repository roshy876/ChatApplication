package com.aoop.ClientServer;

import java.io.Serializable;

public class DataPacket implements Serializable {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "DataPacket [message=" + message + "]";
	}
}
