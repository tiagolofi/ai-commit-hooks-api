package com.github.tiagolofi.mensageria;

public enum Mensagens {
    
    ADICIONADO(new Mensagem("Adicionado com sucesso!")),
    AUTORIZADO(new Mensagem("Acesso autorizado")),
    NAO_AUTORIZADO(new Mensagem("Acesso não autorizado")),
    TOKEN_EXPIRADO(new Mensagem("Token expirado!")),
    LIMITE_USO(new Mensagem("Limite de uso atingido!")),
    TOKEN_INVALIDO(new Mensagem("Token inválido!")),
    EM_PROCESSAMENTO(new Mensagem("Dados em processamento")),
    DELETADO(new Mensagem("As informações foram apagadas")),
    ATUALIZADO(new Mensagem("As informações foram atualizadas"));

    private final Mensagem mensagem;

    private Mensagens (Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

}
