package com.example.abstractdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;

import com.JsonObject;

import android.os.AsyncTask;
import android.util.Log;


public class MyHTTPRequest extends AsyncTask<JsonObject, Void, Void> {
	
    private String ip;
    private String port;
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

	public void send(JsonObject jO) throws IOException, JSONException {
		String body = jO.getJsonObject().toString();
		
		URL url = new URL("http://"+ip+":"+port);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("Content-Length", String.valueOf(body.length()));

		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write(body);
		writer.flush();

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		writer.close();
		reader.close();
	}
	
	public MyHTTPRequest(){
	}

	public MyHTTPRequest(String ip, String port) {
		this.ip = ip;
		this.port = port;
	}

	@Override
	protected Void doInBackground(JsonObject... params) {
		try {
			send(params[0]);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
