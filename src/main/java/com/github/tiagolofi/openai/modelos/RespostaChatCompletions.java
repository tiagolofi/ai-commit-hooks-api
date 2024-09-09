package com.github.tiagolofi.openai.modelos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RespostaChatCompletions {
    
    public String id;
    public String object;
    public long created;
    public String model;
    @JsonProperty("system_fingerprint")
    public String systemFingerprint;
    public List<Choices> choices = new ArrayList<>(); 
    public Usage usage;

    public static class Choices {
        @JsonProperty("finish_reason")
        public String finishReason;
        public int index;
        public MessageResponse message;
        public Object logprobs = null;

    }

    public static class MessageResponse {
        public String content;
        public String role;
    }

    public static class Usage {
        @JsonProperty("completion_tokens")
        public int completionTokens;
        @JsonProperty("prompt_tokens")
        public int promptTokens;
        @JsonProperty("total_tokens")
        public int totalTokens;
    }

}
