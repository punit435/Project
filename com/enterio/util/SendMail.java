package com.enterio.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SendMail 
{
	public void send(String toMailId, String toName, String mailSubject, String mailMessage) throws JSONException, ClientProtocolException, IOException
	{
		String authToken = "";
		HttpClient client = new DefaultHttpClient();
	    HttpPost post = new HttpPost("http://mqm.vanward.co/api/authtoken?apikey=7583777251650227113");
		HttpResponse postResponse = client.execute(post);
		BufferedReader postResponseReader = new BufferedReader(new InputStreamReader(postResponse.getEntity().getContent()));
		String responseString = postResponseReader.readLine();
		postResponseReader.close();
		if(responseString != null)
		{		
			JSONObject postResponseObj = null;
			try 
			{
				postResponseObj = new JSONObject(responseString);
				if(postResponseObj != null)
				{
					authToken = postResponseObj.getString("auth_token");
				}
			}
			catch (JSONException e) 
			{
				authToken = null;
				e.printStackTrace();
			}
		}
		
		if(authToken != null && authToken.length() > 0)
		{
			JSONArray recepientArray = new JSONArray();
			JSONObject recepientObject = new JSONObject();
            recepientObject.put("email", toMailId);
			recepientObject.put("email", "rupakraj998@gmail.com");
			recepientObject.put("name", toName);
			recepientObject.put("type", "to");
			recepientArray.put(recepientObject);

			JSONObject messageObject = new JSONObject();
			messageObject.put("text", mailMessage);
			messageObject.put("subject", mailSubject);
			messageObject.put("from_email", "admin@pgi-intraconnect.in");
			messageObject.put("from_name", "Admin");
			messageObject.put("to", recepientArray);
			
		/*	filename = StringUtil.checkNull(filename);
			if(fileContent != null && filename.length() > 0)
			{
				String text = Base64.encodeBase64String(fileContent);
				JSONArray attachmentArray = new JSONArray();
				JSONObject attachmentObject = new JSONObject();
				attachmentObject.put("type", "application/octet-stream");
				attachmentObject.put("name", filename);
				attachmentObject.put("content", text);
				attachmentArray.put(attachmentObject);
				messageObject.put("attachments", attachmentArray);
			}*/
			
		    post = new HttpPost("http://mqm.vanward.co/api/sendmail?auth_token=" + authToken);
			System.out.println("Mail Utill");

		    StringEntity input = new StringEntity(messageObject.toString());
			input.setContentType("application/json");
			post.setEntity(input);
			
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) 
			{
				System.out.println(line);
			}
		}
	}
}