package com.example.abstractdemo;

import java.io.IOException;

import com.HTTPD;

import media.StartActivities;


public class ComTabel {
	private HTTPD httpd;

    public ComTabel(StartActivities sa) {
		this.httpd = new HTTPD(8080, sa);
		try {
			httpd.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
