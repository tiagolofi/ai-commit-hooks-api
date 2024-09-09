package com.github.tiagolofi.openai;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestHeader;

import com.github.tiagolofi.openai.modelos.RequisicaoGPT4Mini;
import com.github.tiagolofi.openai.modelos.RespostaGPT4Mini;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "openai")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface OpenAI {

    @POST
    RespostaGPT4Mini postGpt(
        RequisicaoGPT4Mini requisicaoGPT4Mini,
        @RestHeader("Authorization") String apiKey
    );

}
