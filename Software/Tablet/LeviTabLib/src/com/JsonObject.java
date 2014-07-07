package com;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonObject {
	public enum Type {
		auth, data
	};

	private Type type;
	private String data;

	public JsonObject(Type type, String data) {
		this.data = data;
		this.type = type;
	}

	public String getDataString() {
		return data;
	}

	public Type getType() {
		return type;
	}
	public JSONObject getJsonObject() throws JSONException{
		JSONObject jO = new JSONObject();
		jO.put("type", type);
		jO.put("data", data);
		return jO;
	}
}