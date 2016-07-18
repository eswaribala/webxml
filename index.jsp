<%@ page language="java"  contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.cts.entities.Location,com.google.gson.Gson,javax.ws.rs.core.MediaType,javax.ws.rs.core.UriBuilder,org.json.simple.JSONObject,com.sun.jersey.api.client.ClientResponse,com.sun.jersey.api.client.Client,com.sun.jersey.api.client.WebResource,com.sun.jersey.api.client.config.ClientConfig,com.sun.jersey.api.client.config.DefaultClientConfig"%>
<%@ include  file="header.jsp"  %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%!
 int number=0;
String name=null;
%>
	<% 
  
            if(request.getParameter("submit") != null) {
                number=Integer.parseInt(request.getParameter("locationId"));  
                name=request.getParameter("locationName");   
              //covert to JSON
  	          Location location =new Location();
  		     	location.setLocationId(number);
  		     	location.setName(name);
  		      
  		      ClientConfig clientConfig = new DefaultClientConfig();
  				Client client=Client.create(clientConfig);
  				WebResource service = client.resource(UriBuilder.fromUri("http://localhost:7070/RESTHIBProject/").build());
  		      ClientResponse cresponse = 
  			    		service.path("rest").path("/EventService/addLocation")
  			    		.type("application/json")
  			    	    .post(ClientResponse.class, new Gson().toJson(location)); 
  			    System.out.println(cresponse); 
            }
	         
	
        %>
       
<section class="formstyle">
<form>
<fieldset>
<legend>Location Information</legend>
<input type="number" placeholder="Location Id" name="locationId" required>
<input type="text" placeholder="Location Name" name="locationName" required>
<input type="submit" name="submit" value="Add">
</fieldset>
</form>
</section>
</body>
</html>