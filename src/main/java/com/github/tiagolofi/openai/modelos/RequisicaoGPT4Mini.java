package com.github.tiagolofi.openai.modelos;

import java.util.ArrayList;
import java.util.List;

public class RequisicaoGPT4Mini {
    
    public String model;
    public List<MessagePrompt> messages;

    public RequisicaoGPT4Mini (Builder builder) {
        this.model = builder.model;
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
        public List<MessagePrompt> messages = new ArrayList<>();

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setMessage(String role, String content) {
            this.messages.add(new MessagePrompt(role, content));
            return this;
        }

        public RequisicaoGPT4Mini build() {
            return new RequisicaoGPT4Mini(this);
        }
    }

}
