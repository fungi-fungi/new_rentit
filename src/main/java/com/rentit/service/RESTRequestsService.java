package com.rentit.service;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sun.jersey.core.util.Base64;


public class RESTRequestsService {
	
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	private String url;
	private String userName;
	private String password;
	private String body;	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int sendPost() throws Exception {
		 				
		URL obj = new URL(this.getUrl());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		
		String encoding = new String(Base64.encode(this.getUserName() + ":" + this.getPassword()));

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Authorization", "Basic " + encoding);
		con.setRequestProperty("Content-Type", "application/xml");
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(this.getBody());
		wr.flush();
		wr.close();
 
		return con.getResponseCode();

	}

}
