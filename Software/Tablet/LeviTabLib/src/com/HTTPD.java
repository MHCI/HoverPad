package com;

import java.net.MalformedURLException;
import java.util.Map;

import media.StartActivities;

import org.json.JSONException;
import org.json.JSONObject;
import com.NanoHTTPD.Response.Status;

import android.util.Log;

public class HTTPD extends NanoHTTPD{
	String tag = "HTTPD";
	StartActivities sa;
	public HTTPD(int port, StartActivities sa) {
		super(port);
		this.sa = sa;
		log("HTTPD lauscht auf port " + port);
	}

	@Override
	public Response serve(String uri, Method method,
			Map<String, String> header, Map<String, String> parms,
			Map<String, String> files) {
		Response res;
		try {
			JSONObject jO = new JSONObject(parms.get("data"));
			if(method.equals(Method.POST)){
				return doPost(uri, jO);
			}else{
				Log.d(tag, "unknown method _" + method + "_");
			}
			JSONObject resJO = new JSONObject();
			resJO.accumulate("result", "OK");
			res = new Response(resJO.toString());
		} catch (JSONException e) {
			res = new Response("Jsonexception");
			res.setStatus(Status.BAD_REQUEST);
		} catch (MalformedURLException e) {
			res = new Response("MalformedUrlException");
			res.setStatus(Status.BAD_REQUEST);
		}
		return res;
	}
	private Response doPost(String uri, JSONObject jO) throws MalformedURLException, JSONException{
		if(jO.getString("type").equals("data") || jO.getString("type").equals("1")){
			JsonObject jO2 = new JsonObject(JsonObject.Type.data, jO.getString("data"));
			sa.startString(jO.getString("data"));
		}
		JSONObject resJO = new JSONObject();
		resJO.accumulate("result", "OK");

		return new Response(resJO.toString());
	}
	private void log(String s){
		Log.d("HTTPD", s);
	}

}
