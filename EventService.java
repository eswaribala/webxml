package com.cts.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cts.dao.EventManager;
import com.cts.entities.Location;
import com.cts.entities.Message;

@Path("/EventService")
public class EventService {
	@POST
	@Path("/addLocation")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response addLocation(Location location)
	{
		Message message=new Message();
		message.setStatus("Not Added");
		System.out.println(location.getLocationId());
		System.out.println(location.getName());
		boolean status = EventManager.AddLocation(location);
		System.out.println(status);
		if(status)
		{
			message.setStatus("Record Added");
		}
		return Response
				 .status(200)
		            .header("Access-Control-Allow-Origin", "*")
		            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
		            .header("Access-Control-Allow-Credentials", "true")
		            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
		            .header("Access-Control-Max-Age", "1209600")
		            .entity(message)
		            .build();
	}
	
	
	@GET
	@Path("/getAll")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getAll()
	{
		GenericEntity<List<Location>> genentity = 
				new GenericEntity<List<Location>>(EventManager.getAll()) {};
	
		
		return Response
				 .status(200)
		            .header("Access-Control-Allow-Origin", "*")
		            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
		            .header("Access-Control-Allow-Credentials", "true")
		            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
		            .header("Access-Control-Max-Age", "1209600")
		            .entity(genentity)
		            .build();
	}
}
