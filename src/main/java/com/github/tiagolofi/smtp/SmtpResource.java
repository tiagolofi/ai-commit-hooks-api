package com.github.tiagolofi.smtp;

import io.quarkus.mailer.MailTemplate;
import io.quarkus.qute.CheckedTemplate;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;

public class SmtpResource {
    
    @CheckedTemplate
    static class Stmp {
        private Stmp() {}
        public static native MailTemplate.MailTemplateInstance soliticacao();
        public static native MailTemplate.MailTemplateInstance envioToken(String token);
        public static native MailTemplate.MailTemplateInstance aprovacao(String email);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> enviarEmailConvite() {
        return Stmp.aprovacao()
            .to("jejeco75@gmail.com")
            .subject("COALA SHAKES #CadastroNaoAutorizado")
            .send();
    }
}
