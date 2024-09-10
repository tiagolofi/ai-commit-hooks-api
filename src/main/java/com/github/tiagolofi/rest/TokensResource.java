package com.github.tiagolofi.rest;

import org.jboss.resteasy.reactive.RestHeader;
import org.jboss.resteasy.reactive.RestQuery;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.tiagolofi.mensageria.Mensagem;
import com.github.tiagolofi.mensageria.Mensagens;
import com.github.tiagolofi.mongo.Tokens;

import io.micrometer.core.annotation.Timed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/token")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class TokensResource {
        
    @RestHeader("Token-Autorizacao")
    String tokenAutorizacao;

    @POST
    @Path("/novo")
    @Timed(value = "token", extraTags = {"metodo", "novo"}, percentiles = {0.5, 0.95, 0.99})
    public Response novo(NovoToken novoToken) {
        Tokens token = Tokens.builder()
            .setEmail(novoToken.email)
            .setToken()
            .setExpiresIn(novoToken.durationDays)
            .setBlocked(false)
            .setPending(true)
            .setLimit(novoToken.limitRequest)
            .build();
        
        token.persist();

        return Response.status(202)
            .entity(new Mensagem("Token em processamento", token.token))
            .build();
    }

    @PUT
    @Path("/aprovar")
    @Timed(value = "token", extraTags = {"metodo", "aprovar"}, percentiles = {0.5, 0.95, 0.99})
    public Response aprovar(@RestQuery String tokenPending) {

        Tokens token = Tokens.getTokenByToken(tokenPending);
        token.aprovar();
        token.update();

        return Response.status(200)
            .entity(Mensagens.ATUALIZADO.getMensagem())
            .build();
    }

    @PUT
    @Path("/bloquear")
    @Timed(value = "token", extraTags = {"metodo", "bloquear"}, percentiles = {0.5, 0.95, 0.99})
    public Response bloquear(@RestQuery String tokenBlock) {

        Tokens token = Tokens.getTokenByToken(tokenBlock);
        token.bloquear();
        token.update();

        return Response.status(200)
            .entity(Mensagens.ATUALIZADO.getMensagem())
            .build();
    }

    @PUT
    @Path("/desbloquear")
    @Timed(value = "token", extraTags = {"metodo", "desbloquear"}, percentiles = {0.5, 0.95, 0.99})
    public Response desbloquear(@RestQuery String tokenBlock) {

        Tokens token = Tokens.getTokenByToken(tokenBlock);
        token.desbloquear();
        token.update();

        return Response.status(200)
            .entity(Mensagens.ATUALIZADO.getMensagem())
            .build();
    }

    public static class NovoToken {
        public String email;
        @JsonProperty("duration_days")
        public int durationDays;
        @JsonProperty("limit_request")
        public int limitRequest;
    }
    
}
