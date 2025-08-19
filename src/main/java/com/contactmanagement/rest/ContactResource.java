package com.contactmanagement.rest;

import com.contactmanagement.dto.ContactDTO;
import com.contactmanagement.entity.ContactEntity;
import com.contactmanagement.service.ContactService;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/contacts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {

    @Inject
    private ContactService contactService;

    @GET
    @Path("/user/{userId}")
    public List<ContactDTO> getContactsByUser(@PathParam("userId") String userId) {
        return contactService.getContactsByUserId(userId);
    }

    @POST
    public Response createContact(ContactDTO contactDTO) {
        try {
            ContactEntity entity = contactService.createContact(contactDTO);
            ContactDTO responseDto = new ContactDTO(
                    entity.getId(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getEmail(),
                    entity.getPhone(),
                    Long.toString(entity.getUser().getId())
            );
            return Response.status(Response.Status.CREATED).entity(responseDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Erreur : " + e.getMessage())
                           .build();
        }
    }
     // ---------------- Get all contacts by userId ----------------
}

