package com.github.tiagolofi.rest;

import org.jboss.resteasy.reactive.RestHeader;

import com.github.tiagolofi.openai.modelos.RespostaChatCompletions;
import com.github.tiagolofi.service.AiCommitHookService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestScoped
@Path("/commit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
public class AiCommitHookResource {

    @Inject
    AiCommitHookService service;

    @RestHeader("Token-Consumo") 
    String tokenConsumo;

    @POST
    @Path("/gpt-4o-mini")
    public RespostaGPT4oMini gpt4(String gitDiff) {
        RespostaChatCompletions resposta = service.commitGpt(gitDiff);
        return new RespostaGPT4oMini(resposta.choices.getFirst().message.content);
    }

    public class RespostaGPT4oMini {
        public String respostaGPT4oMini;
        public RespostaGPT4oMini(String respostaGPT4oMini) {
            this.respostaGPT4oMini = respostaGPT4oMini;
        }
    }
 
}