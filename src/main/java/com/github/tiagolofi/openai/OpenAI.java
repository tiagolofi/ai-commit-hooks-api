package com.github.tiagolofi.openai;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestHeader;

import com.github.tiagolofi.openai.modelos.RequisicaoChatCompletions;
import com.github.tiagolofi.openai.modelos.RespostaChatCompletions;

import io.micrometer.core.annotation.Timed;
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
    @Timed(value = "openai", extraTags = {"endpoint", "chat-completions"}, percentiles = {0.5, 0.95, 0.99})
    RespostaChatCompletions postGpt(
        RequisicaoChatCompletions requisicaoGPT4Mini,
        @RestHeader("Authorization") String apiKey
    );

}
