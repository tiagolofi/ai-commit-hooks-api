package com.github.tiagolofi.filtros;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.github.tiagolofi.mensageria.Mensagens;
import com.github.tiagolofi.mongo.Token;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Arrays;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class FiltroToken implements ContainerRequestFilter {
    
    @ConfigProperty(name = "TOKEN_AUTORIZACAO")
    String AUTORIZACAO;

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String tokenAutorizacao = requestContext.getHeaderString("Token-Autorizacao");

        List<String> paths = Arrays.asList("/token/aprovar", "/token/bloquear", "/token/novo", "/token/desbloquear");
        String path = requestContext.getUriInfo().getPath();

        if (paths.stream().noneMatch(path::contains)) {
            String tokenHeader = requestContext.getHeaderString("Token-Consumo");

            Token token = Token.getTokenByToken(tokenHeader);
    
            long agora = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toEpochSecond();
    
            if (token == null) {
                requestContext.abortWith(
                    Response.status(404)
                        .entity(Mensagens.TOKEN_INVALIDO.getMensagem())
                        .build()
                    ); 
            }

            if (token.blocked == true || token.pending == true) {
                requestContext.abortWith(
                    Response.status(401)
                        .entity(Mensagens.NAO_AUTORIZADO.getMensagem())
                        .build()
                    );
            }
    
            if (token.limit < 1) {
                requestContext.abortWith(
                    Response.status(401)
                        .entity(Mensagens.LIMITE_USO.getMensagem())
                        .build()
                    );
            }

            token.debitarChamada();
            token.update();

            if (agora > token.expiresIn) {
                requestContext.abortWith(
                    Response.status(401)
                        .entity(Mensagens.TOKEN_EXPIRADO.getMensagem())
                        .build()
                    );
            }
        } else {
            if (!tokenAutorizacao.equals(AUTORIZACAO)) {
                requestContext.abortWith(
                    Response.status(401)
                        .entity(Mensagens.NAO_AUTORIZADO.getMensagem())
                        .build()
                    );
            }
        }

    }

}
