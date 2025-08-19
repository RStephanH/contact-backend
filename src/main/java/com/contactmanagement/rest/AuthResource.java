package com.contactmanagement.rest;

import com.contactmanagement.dto.LoginRequest;
import com.contactmanagement.dto.LoginResponse;
import com.contactmanagement.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.enterprise.context.RequestScoped;
import org.mindrot.jbcrypt.BCrypt;

@Path("/login")
@RequestScoped
public class AuthResource {

    @PersistenceContext(unitName = "contactPU")
    private EntityManager em;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response login(LoginRequest loginRequest) {
        try {
            UserEntity user = em.createQuery(
                    "SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class)
                    .setParameter("username", loginRequest.getUsername())
                    .getSingleResult();

            if (BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {
                LoginResponse resp = new LoginResponse(
                        true,
                        "Login successful",
                        Long.toString(user.getId()),
                        user.getUsername(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getMail()
                );
                return Response.ok(resp).build();
            } else {
                LoginResponse resp = new LoginResponse(false, "Invalid username or password", null, null, null, null, null);
                return Response.status(Response.Status.UNAUTHORIZED).entity(resp).build();
            }

        } catch (jakarta.persistence.NoResultException e) {
            LoginResponse resp = new LoginResponse(false, "Invalid username or password", null, null, null, null, null);
            return Response.status(Response.Status.UNAUTHORIZED).entity(resp).build();
        }
    }
}

