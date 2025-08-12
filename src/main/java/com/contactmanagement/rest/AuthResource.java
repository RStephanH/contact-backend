package com.contactmanagement.rest;

import com.contactmanagement.entity.UserEntity;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.enterprise.context.RequestScoped;

@Path("/login")
@RequestScoped
public class AuthResource {

    @PersistenceContext(unitName = "contactPU")
    private EntityManager em;

    public static class LoginRequest {
        public String username;
        public String password;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response login(LoginRequest loginRequest) {
        try {
            UserEntity user = em.createQuery(
                    "SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password", UserEntity.class)
                    .setParameter("username", loginRequest.username)
                    .setParameter("password", loginRequest.password)
                    .getSingleResult();

            return Response.ok()
                    .entity("{\"message\":\"Login successful\", \"username\":\"" + user.getUsername() + "\"}")
                    .build();
        } catch (jakarta.persistence.NoResultException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"Invalid username or password\"}")
                    .build();
        }
    }
}

