package com.github.tiagolofi.rest;

import org.jboss.resteasy.reactive.RestHeader;
import org.jboss.resteasy.reactive.RestQuery;

import com.github.tiagolofi.mensageria.Mensagem;
import com.github.tiagolofi.mensageria.Mensagens;
import com.github.tiagolofi.mongo.Token;

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
public class TokenResource {
        
    @RestHeader("Token-Autorizacao")
    String tokenAutorizacao;

    @POST
    @Path("/novo")
    public Response novo(NovoToken novoToken) {
        Token token = Token.builder()
            .setEmail(novoToken.email)
            .setToken()
            .setExpiresIn(novoToken.durationDays)
            .setBlocked(false)
            .setPending(true)
            .setLimit(novoToken.limit)
            .build();
        
        token.persist();

        return Response.status(202)
            .entity(new Mensagem("Token em processamento", token.token))
            .build();
    }

    @PUT
    @Path("/aprovar")
    public Response aprovar(@RestQuery String tokenPending) {

        Token token = Token.getTokenByToken(tokenPending);
        token.aprovar();
        token.update();

        return Response.status(200)
            .entity(Mensagens.ATUALIZADO.getMensagem())
            .build();
    }

    @PUT
    @Path("/bloquear")
    public Response bloquear(@RestQuery String tokenBlock) {

        Token token = Token.getTokenByToken(tokenBlock);
        token.bloquear();
        token.update();

        return Response.status(200)
            .entity(Mensagens.ATUALIZADO.getMensagem())
            .build();
    }

    @PUT
    @Path("/desbloquear")
    public Response desbloquear(@RestQuery String tokenBlock) {

        Token token = Token.getTokenByToken(tokenBlock);
        token.desbloquear();
        token.update();

        return Response.status(200)
            .entity(Mensagens.ATUALIZADO.getMensagem())
            .build();
    }

    public static class NovoToken {
        public String email;
        public int durationDays;
        public int limit;
    }
    
}
