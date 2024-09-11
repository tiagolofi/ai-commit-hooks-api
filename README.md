# ai-commit-hooks-api

Serviço que gera commits usando o ChatGPT a partir de padrões de boas práticas

## Pré-requsitos

- Java 22 ou 21
- Maven 3.9.5

## Como configurar

Em um arquivo `.env` configure as variáveis da seguinte forma:

- OPENAI_API_KEY=sk-proj...
- QUARKUS_MONGODB_CONNECTION_STRING=mongodb+srv://...
- TOKEN_AUTORIZACAO=eX1s21nd0S...

## Como rodar o projeto

O comando a seguir irá rodar o projeto no modo desenvolvedor, exibindo a aplicação em `http://localhost:8080`:

```
mvn quarkus:dev
```

## Contribuição

1. Contribua escrevendo os testes e sugerindo pontos para refatoração, odeio escrever testes... 😭;
2. Outros modelos da OpenAI serão incluídos e novos projetos serão gerados a partir deste.

## Testes

Este é um teste de commit manual.