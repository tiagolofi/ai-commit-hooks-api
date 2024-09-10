package com.github.tiagolofi.openai.modelos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequisicaoChatCompletions {
    
    public String model;
    @JsonProperty("max_tokens")
    public int maxTokens;
    public List<MessagePrompt> messages;

    public RequisicaoChatCompletions (Builder builder) {
        this.model = builder.model;
        this.maxTokens = builder.maxTokens;
        this.messages = builder.messages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class MessagePrompt {
    
        public String role;
        public String content;
    
        public MessagePrompt (String role, String content) {
            this.role = role;
            this.content = content;
        }
        
    }

    public static class Builder {
        public String model;
        public int maxTokens;
        public List<MessagePrompt> messages = new ArrayList<>();

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setMaxTokens(int maxTokens) {
            this.maxTokens = maxTokens;
            return this;
        }

        public Builder setMessage(String role, String content) {
            this.messages.add(new MessagePrompt(role, content));
            return this;
        }

        public RequisicaoChatCompletions build() {
            return new RequisicaoChatCompletions(this);
        }
    }

}
