package com.contactmanagement.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.contactmanagement.dto.UserCredentials;

@Path("/login") //This becomes: /api/login
public class AuthResource {
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response login(UserCredentials creds){
    if ("admin".equals(creds.username) && ("1234".equals(creds.password))) {
      return Response.ok("{\"status:\"\"success\",\"message\":\"Welcome admin!\"}")
             .build();
    } else {
      return Response.ok("{\"status:\"\"failed\",\"message\":\"Wrong credentials!\"}")
             .build();
      
    }
  }

  
}

