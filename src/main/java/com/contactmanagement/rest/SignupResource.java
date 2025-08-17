package com.contactmanagement.rest;

import com.contactmanagement.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.enterprise.context.RequestScoped;
import org.mindrot.jbcrypt.BCrypt;

@Path("/signup")
@RequestScoped
public class SignupResource {

    @PersistenceContext(unitName = "contactPU")
    private EntityManager em;

    // Request payload object
    public static class SignupRequest {
        public String firstName;
        public String lastName;
        public String email;
        public String username;
        public String password;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response signup(SignupRequest req) {
        try {
            // Check if username already exists
            long existing = em.createQuery(
                    "SELECT COUNT(u) FROM UserEntity u WHERE u.username = :username", Long.class)
                    .setParameter("username", req.username)
                    .getSingleResult();

            if (existing > 0) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("{\"error\":\"Username already taken\"}")
                        .build();
            }

            // Hash the password using BCrypt
            String hashedPassword = BCrypt.hashpw(req.password, BCrypt.gensalt());

            // Create new UserEntity
            UserEntity user = new UserEntity();
            user.setFirstName(req.firstName);
            user.setLastName(req.lastName);
            user.setMail(req.email);
            user.setUsername(req.username);
            user.setPassword(hashedPassword);

            // Persist user
            em.persist(user);

            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Signup successful\", \"username\":\"" + user.getUsername() + "\"}")
                    .build();

        } catch (Exception e) {
            return Response.serverError()
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}

