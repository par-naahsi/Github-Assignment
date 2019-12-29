package com.assignment.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

	public static String get(String urlString) throws Exception  {
		return get(urlString,null,null);
	}

	public static String get(String urlString, String username, String password) throws Exception  {

		StringBuffer output = new StringBuffer();
		try {
			LOGGER.debug("GET URL:" + urlString);
			URL url = new URL(urlString.trim());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("content-type", "application/json");
			LOGGER.debug("User details: " + username + ":" + password);
			if (username != null) {
				String userCredentials = username.trim() + ":" + password.trim();
				String encodedCredentials = "Basic "
						+ new String(Base64.encodeBase64URLSafe(userCredentials.getBytes())).replaceAll("\n", "");
				LOGGER.debug("Encoded Credentials : " + encodedCredentials);
				conn.setRequestProperty("Authorization", encodedCredentials);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String line;
			while ((line = br.readLine()) != null) {
				output.append(line);
			}

			LOGGER.debug("Response code:" + conn.getResponseCode());

			LOGGER.debug("Response string :" + output.toString());

			if (conn.getResponseCode() != 200) {
				throw new Exception("Exception occured:Err code:" + conn.getResponseCode());
			}

			LOGGER.debug("Content type:" + conn.getContentType());

			if (null != conn.getContentType() && !conn.getContentType().toLowerCase().contains("application/json")) {
				throw new Exception("Failed : HTTP error code : " + conn.getContentType());
			}

			conn.disconnect();

		} catch (IOException ioexception) {
			//throw new Exception( "Failed : IO Exception occurred");
			return "Not found";
		} catch (Exception ex) {
			throw new Exception(ex);
		}

		return output.toString();
	}



}
