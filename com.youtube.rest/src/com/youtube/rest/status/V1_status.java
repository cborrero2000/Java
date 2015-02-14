package com.youtube.rest.status;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//http://169.254.6.143:8080/com.youtube.rest/api/v1/status/
@Path("/v1/status")
public class V1_status {

	private static final String api_version="00.01.00";
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String returnTitle(){
		return "<p>Java Web Service - OK<p>";
	}
	//http://169.254.6.143:8080/com.youtube.rest/api/v1/status/version
	//http://localhost:8080/com.youtube.rest/api/v1/status/version
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "<p>Version:<p>" + api_version;
	}
	
	// ASK the teacher how to return a gson object here

	//http://169.254.6.143:8080/com.youtube.rest/api/v1/status/gson
	//http://localhost:8080/com.youtube.rest/api/v1/status/gson
	@Path("/gson")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnGson(){
		//Ex.: String jsonStr = "{\"my_key\": \"my_value\"}";
		return "{\"users\":[{\"first_name\": \"Lee\", \"last_name\": \"Adama\", \"phone_number\": \"616-555-4280\", \"email_address\": \"apollo@bsg.org\", \"notes\": \"Commander's son\"}]}";		
		
		
		/*{"users":[{"first_name": "Lee", "last_name": "Adama", "phone_number": "616-555-4280", "email_address": "apollo@bsg.org", "notes": "Commander's son"},
{"first_name": "Karl", "last_name": "Agathon", "phone_number": "616-555-1121", "email_address": "helo@bsg.org", "notes": "Cylon lover"},
{"first_name": "Galen", "last_name": "Tyrol", "phone_number": "769-555-8281", "email_address": "chief@bsg.org", "notes": "Secret Cylon"},
{"first_name": "Number", "last_name": "Six", "phone_number": "635-555-9900", "email_address": "six@cylons.org", "notes": "Blatant Cylon"},
{"first_name": "Gaius", "last_name": "Baltar", "phone_number": "864-555-7591", "email_address": "gaius@bsg.org", "notes": "Coward"},
{"first_name": "William", "last_name": "Adama", "phone_number": "414-555-9812", "email_address": "husker@bsg.org", "notes": "Certified bad ass"},
{"first_name": "Kara", "last_name": "Thrace", "phone_number": "221-555-9017", "email_address": "starbuck@bsg.org", "notes": "Viper pilot extraordinaire"},
{"first_name": "Laura", "last_name": "Roslin", "phone_number": "775-555-0891", "email_address": "prez@colonialone.org", "notes": "Secretary of Education turned leader"},
{"first_name": "Saul", "last_name": "Tigh", "phone_number": "666-555-1111", "email_address": "xo@bsg.org", "notes": "Sort of a futuristic pirate"}]}
*/
		
		
		/*1. GET method
		When URI pattern �/json/metallica/get� is requested, the Metallica classic song �Enter Sandman� will be returned in JSON format.

		{
			"singer":"Metallica",
			"title":"Enter Sandman"
		}*/
		
		
		
		
		
		
		
		
		
		
	}
	
}
